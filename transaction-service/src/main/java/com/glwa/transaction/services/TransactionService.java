package com.glwa.transaction.services;

import com.glwa.transaction.dtos.*;
import com.glwa.transaction.exceptions.BalanceNotSufficientException;
import com.glwa.transaction.exceptions.BankAccountNotFoundException;
import com.glwa.transaction.exceptions.OperationNotFoundException;

import java.util.List;

public interface TransactionService {
    DebitDTO debit(DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException;

    CreditDTO credit(CreditDTO creditDTO) throws BankAccountNotFoundException;

    TransferDTO transfer(TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<TransactionDTO> getAllOperationsByAccountId(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;

    TransactionDTO getOperationById(Long id) throws OperationNotFoundException;
}
