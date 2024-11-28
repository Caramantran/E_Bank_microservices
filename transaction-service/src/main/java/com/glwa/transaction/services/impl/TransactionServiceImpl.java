package com.glwa.transaction.services.impl;

import com.glwa.transaction.domain.Transaction;
import com.glwa.transaction.dtos.*;
import com.glwa.transaction.enums.OperationType;
import com.glwa.transaction.exceptions.BalanceNotSufficientException;
import com.glwa.transaction.exceptions.OperationNotFoundException;
import com.glwa.transaction.feign.AccountFeignClient;
import com.glwa.transaction.feign.dtos.*;
import com.glwa.transaction.repositories.TransactionRepository;
import com.glwa.transaction.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository operationRepository;
    private final AccountFeignClient accountFeignClient;

    public TransactionServiceImpl(TransactionRepository operationRepository, AccountFeignClient accountFeignClient) {
        this.operationRepository = operationRepository;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    public DebitDTO debit(DebitDTO debitDTO) throws BalanceNotSufficientException {
        AccountDTO account = accountFeignClient.findById(debitDTO.accountId());
        if(account.interestRate() == -1){
            if(debitDTO.amount().compareTo(account.balance()) > 0) {
                throw new BalanceNotSufficientException("Balance not sufficient");
            }
            BigDecimal balance = account.balance().subtract(debitDTO.amount());
            CurrentAccountDTO accountDTO = accountFeignClient.updateCurrentAccount(new UpdateCurrentAccountDTO(
                    account.id(), balance, account.overDraft(), account.status()));
            return saveDebitDTO(debitDTO, account, accountDTO.getId());
        }else{
            BigDecimal balance = account.balance().subtract(debitDTO.amount());
            SavingAccountDTO accountDTO = accountFeignClient.updateSavingAccount(new UpdateSavingAccountDTO(account.id(), balance,
                    account.interestRate(), account.status()));
            return saveDebitDTO(debitDTO, account, accountDTO.getId());
        }
    }

    @Override
    public CreditDTO credit(CreditDTO creditDTO){
        AccountDTO account = accountFeignClient.findById(creditDTO.accountId());
        if(account.interestRate() == -1){
            CurrentAccountDTO accountDTO = accountFeignClient
                    .updateCurrentAccount(new UpdateCurrentAccountDTO(account.id(),
                            account.balance().add(creditDTO.amount()), account.overDraft(), account.status()));
            return saveCreditDTO(creditDTO, account, accountDTO.getId());
        }else{
            SavingAccountDTO accountDTO = accountFeignClient.updateSavingAccount(new UpdateSavingAccountDTO(account.id(),
                    account.balance().add(creditDTO.amount()), account.interestRate(), account.status()));
            return saveCreditDTO(creditDTO, account, accountDTO.getId());
        }
    }

    @Override
    public TransferDTO transfer(TransferDTO transferDTO) throws BalanceNotSufficientException {
        DebitDTO debitDTO = new DebitDTO(transferDTO.accountSource(), transferDTO.amount(), transferDTO.motive());
        DebitDTO debitDTOSaved = debit(debitDTO);
        CreditDTO creditDTO = new CreditDTO(transferDTO.accountDestination(), transferDTO.amount(), transferDTO.motive());
        CreditDTO creditDTOSaved = credit(creditDTO);
        log.info("transfer successfully");
        return new TransferDTO(debitDTOSaved.accountId(), creditDTOSaved.accountId(), creditDTOSaved.amount(), debitDTOSaved.motive());
    }

    @Override
    public List<TransactionDTO> getAllOperationsByAccountId(String accountId) {
        List<Transaction> transactions = operationRepository.findByBankAccountId(accountId);
        log.info("operations found");
        return transactions.stream().map(this::fromOperation).toList();
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size){
        AccountDTO account = accountFeignClient.findById(accountId);
        return findAccountHistoryDTO(accountId, page, size, account.id(), account.balance());
    }

    @Override
    public TransactionDTO getOperationById(Long id) throws OperationNotFoundException {
        Transaction transaction = operationRepository.findById(id)
                .orElseThrow(()-> new OperationNotFoundException("Operation Not Found"));
        log.info("operation found");
        return fromOperation(transaction);
    }


    private AccountHistoryDTO findAccountHistoryDTO(String accountId, int page, int size, String id, BigDecimal balance) {
        Page<Transaction> accountOperations = operationRepository.findByBankAccountIdOrderByDateDesc(accountId, PageRequest.of(page, size));
        List<TransactionDTO> transactionDTOList = accountOperations.getContent().stream().map(this::fromOperation).toList();
        log.info("operation(s) pages found");
        return new AccountHistoryDTO(id, balance, page, accountOperations.getTotalPages(), size, transactionDTOList);
    }

    private TransactionDTO fromOperation(Transaction transaction){
        return new TransactionDTO(transaction.getId(), transaction.getDate(), transaction.getAmount(), transaction.getType(), transaction.getDescription());
    }

    private CreditDTO saveCreditDTO(CreditDTO creditDTO, AccountDTO account, String id) {
        Transaction transaction = Transaction.builder()
                .amount(creditDTO.amount())
                .bankAccountId(account.id())
                .date(new Date())
                .description(creditDTO.motive())
                .type(OperationType.CREDIT)
                .build();
        Transaction transactionSaved = operationRepository.save(transaction);
        log.info("account well credited");
        return new CreditDTO(id, transactionSaved.getAmount(), transactionSaved.getDescription());
    }

    private DebitDTO saveDebitDTO(DebitDTO debitDTO, AccountDTO account, String id) {
        Transaction transaction = Transaction.builder()
                .amount(debitDTO.amount())
                .bankAccountId(account.id())
                .date(new Date())
                .description(debitDTO.motive())
                .type(OperationType.CREDIT)
                .build();
        Transaction transactionSaved = operationRepository.save(transaction);
        log.info("account well debited");
        return new DebitDTO(id, transactionSaved.getAmount(), transactionSaved.getDescription());
    }

}
