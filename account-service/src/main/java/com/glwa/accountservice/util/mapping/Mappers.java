package com.glwa.accountservice.util.mapping;

import com.glwa.accountservice.dtos.AccountDTO;
import com.glwa.accountservice.dtos.BankAccountDTO;
import com.glwa.accountservice.dtos.CurrentAccountDTO;
import com.glwa.accountservice.dtos.SavingAccountDTO;
import com.glwa.accountservice.domain.BankAccount;
import com.glwa.accountservice.domain.CurrentAccount;
import com.glwa.accountservice.domain.SavingAccount;

import java.util.List;

public interface Mappers {
    SavingAccountDTO fromSavingAccount(SavingAccount savingAccount);
    SavingAccount fromSavingAccountDTO(SavingAccountDTO savingBankAccountDTO);
    CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount);
    CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentBankAccountDTO);
    BankAccountDTO fromBankAccount(BankAccount bankAccount);
    List<BankAccountDTO> fromListOfBankAccount(List<BankAccount> bankAccounts);
    List<AccountDTO> fromListOfAccount(List<BankAccount> accounts);
}
