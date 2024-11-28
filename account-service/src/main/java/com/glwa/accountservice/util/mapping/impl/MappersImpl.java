package com.glwa.accountservice.util.mapping.impl;

import com.glwa.accountservice.dtos.AccountDTO;
import com.glwa.accountservice.dtos.BankAccountDTO;
import com.glwa.accountservice.dtos.CurrentAccountDTO;
import com.glwa.accountservice.dtos.SavingAccountDTO;
import com.glwa.accountservice.domain.BankAccount;
import com.glwa.accountservice.domain.CurrentAccount;
import com.glwa.accountservice.domain.SavingAccount;
import com.glwa.accountservice.util.mapping.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappersImpl implements Mappers {

    @Override
    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingAccountDTO;
    }

    @Override
    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingBankAccountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
        return savingAccount;
    }

    @Override
    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentAccountDTO;
    }

    @Override
    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO, currentAccount);
        return currentAccount;
    }

    @Override
    public BankAccountDTO fromBankAccount(BankAccount bankAccount) {
        if(bankAccount instanceof SavingAccount s) {
            return fromSavingAccount(s);
        }
        CurrentAccount c = (CurrentAccount) bankAccount;
        return fromCurrentAccount(c);
    }

    @Override
    public List<BankAccountDTO> fromListOfBankAccount(List<BankAccount> bankAccounts) {
        return bankAccounts.stream().map(this::fromBankAccount).toList();
    }

    @Override
    public List<AccountDTO> fromListOfAccount(List<BankAccount> accounts) {
        return accounts.stream().map(this::fromAccount).toList();
    }

    public AccountDTO fromAccount(BankAccount account) {
        return fromCurrentAccount(account);
    }

    public AccountDTO fromCurrentAccount(BankAccount currentAccount) {
        return new AccountDTO(currentAccount.getId(),currentAccount.getBalance(),currentAccount.getCreatedAt(),currentAccount.getStatus(),currentAccount.getCustomerId(),0,0, currentAccount.getRib());
    }
}
