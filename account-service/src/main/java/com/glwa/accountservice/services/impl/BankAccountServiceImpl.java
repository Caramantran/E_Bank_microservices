package com.glwa.accountservice.services.impl;

import com.glwa.accountservice.dtos.*;
import com.glwa.accountservice.domain.BankAccount;
import com.glwa.accountservice.domain.CurrentAccount;
import com.glwa.accountservice.domain.SavingAccount;
import com.glwa.accountservice.enums.AccountStatus;
import com.glwa.accountservice.exceptions.BankAccountNotFoundException;
import com.glwa.accountservice.exceptions.CommandRejectedException;
import com.glwa.accountservice.exceptions.CustomerNotFoundException;
import com.glwa.accountservice.feign.CustomerFeignService;
import com.glwa.accountservice.util.idgenerator.IdGenerator;
import com.glwa.accountservice.util.mapping.Mappers;
import com.glwa.accountservice.repositories.BankAccountRepository;
import com.glwa.accountservice.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerFeignService customerFeignService;
    private final Mappers mappers;
    private final IdGenerator idGenerator;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, CustomerFeignService customerFeignService, Mappers mappers, IdGenerator idGenerator) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerFeignService = customerFeignService;
        this.mappers = mappers;
        this.idGenerator = idGenerator;
    }


    @Override
    public List<BankAccountDTO> getAllBankAccount() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return mappers.fromListOfBankAccount(bankAccounts);
    }

    @Override
    public BankAccountDTO getBankAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow( () -> new BankAccountNotFoundException("BankAccount not found"));
        log.info("BankAccount found");
        if(bankAccount instanceof SavingAccount savingAccount) {
            return mappers.fromSavingAccount(savingAccount);
        }
        else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return mappers.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(SaveCurrentAccountDTO savedAccountDTO) throws CustomerNotFoundException {

        CustomerDTO customer = Optional.ofNullable(getCustomerByCin(savedAccountDTO.cin()))
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(idGenerator.autoGenerate());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(BigDecimal.valueOf(10000));
        currentAccount.setCustomerId(customer.id());
        currentAccount.setStatus(AccountStatus.ACTIVATED);
        currentAccount.setRib(savedAccountDTO.rib());
        //currentAccount.setOverDraft(savedAccountDTO.overDraft());
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        log.info("current bank account saved");
        return mappers.fromCurrentAccount(savedBankAccount);
    }

    @Override
    public SavingAccountDTO saveSavingAccount(SaveSavingAccountDTO savingAccountDTO) throws CustomerNotFoundException {
        CustomerDTO customerDTO = getCustomerById(savingAccountDTO.customerId());
        if(customerDTO == null){
            throw new CustomerNotFoundException("customer not found");
        }
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(idGenerator.autoGenerate());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(savingAccountDTO.initialBalance());
        savingAccount.setCustomerId(customerDTO.id());
        savingAccount.setStatus(AccountStatus.ACTIVATED);
        savingAccount.setInterestRate(savingAccountDTO.interestRate());
        SavingAccount account = bankAccountRepository.save(savingAccount);
        log.info("SavingBankAccount saved successfully");
        return mappers.fromSavingAccount(account);
    }

    @Override
    public CurrentAccountDTO updateCurrentAccount(UpdateCurrentAccountDTO accountDTO) throws BankAccountNotFoundException, CommandRejectedException {
        BankAccount bankAccount = bankAccountRepository.findById(accountDTO.id())
                .orElseThrow( ()-> new BankAccountNotFoundException("Current Bank Account Not Found"));
        if(bankAccount instanceof CurrentAccount currentAccount){
            currentAccount.setStatus(accountDTO.status());
            currentAccount.setBalance(accountDTO.balance());
            currentAccount.setOverDraft(accountDTO.overDraft());
            currentAccount.setCustomerId(bankAccount.getCustomerId());
            currentAccount.setCreatedAt(bankAccount.getCreatedAt());
            currentAccount.setId(bankAccount.getId());
            currentAccount.setRib(bankAccount.getRib());
            CurrentAccount current = bankAccountRepository.save(currentAccount);
            log.info("Current Bank Account Update");
            return mappers.fromCurrentAccount(current);
        }else{
            throw new CommandRejectedException("id you gave does not match that of a current account");
        }
    }

    @Override
    public SavingAccountDTO updateSavingAccount(UpdateSavingAccountDTO accountDTO) throws BankAccountNotFoundException, CommandRejectedException {
        BankAccount bankAccount = bankAccountRepository.findById(accountDTO.id())
                .orElseThrow( ()-> new BankAccountNotFoundException("Current Bank Account Not Found"));
        if(bankAccount instanceof SavingAccount savingAccount){
            savingAccount.setId(bankAccount.getId());
            savingAccount.setCreatedAt(bankAccount.getCreatedAt());
            savingAccount.setCustomerId(bankAccount.getCustomerId());
            savingAccount.setBalance(accountDTO.balance());
            savingAccount.setInterestRate(accountDTO.interestRate());
            savingAccount.setStatus(accountDTO.status());
            savingAccount.setRib(bankAccount.getRib());
            SavingAccount saving = bankAccountRepository.save(savingAccount);
            log.info("Current Bank Account Update");
            return mappers.fromSavingAccount(saving);
        }else{
            throw new CommandRejectedException("id you gave does not match that of a saving account");
        }
    }

    @Override
    public AccountDTO getAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow( () -> new BankAccountNotFoundException("BankAccount not found"));
        log.info("BankAccount found");
        if(bankAccount instanceof SavingAccount s) {
            return new AccountDTO(s.getId(), s.getBalance(), s.getCreatedAt(),s.getStatus(),
                    s.getCustomerId(), -1, s.getInterestRate(), s.getRib());
        }
        else {
            CurrentAccount c = (CurrentAccount) bankAccount;
            return new AccountDTO(c.getId(), c.getBalance(), c.getCreatedAt(),c.getStatus(),
                    c.getCustomerId(), c.getOverDraft(), -1, c.getRib());
        }
    }

    @Override
    public List<AccountDTO> getAllAccountByCustomerId(String cin) {
        var customerDTO = customerFeignService.getCustomerById(cin);
        List<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomerId(customerDTO.id());
        return mappers.fromListOfAccount(bankAccounts);
    }


    private CustomerDTO getCustomerById(String id) {
        try{
            return customerFeignService.getCustomerById(id);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    private CustomerDTO getCustomerByCin(String cin) {
        try{
            return customerFeignService.getCustomerByCin(cin);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
