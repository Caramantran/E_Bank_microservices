package com.glwa.accountservice.restcontroller;

import com.glwa.accountservice.dtos.*;
import com.glwa.accountservice.exceptions.BankAccountNotFoundException;
import com.glwa.accountservice.exceptions.CommandRejectedException;
import com.glwa.accountservice.exceptions.CustomerNotFoundException;
import com.glwa.accountservice.services.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountRestController {

    private final BankAccountService bankAccountService;

    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/find/{id}")
    public AccountDTO getAccountById(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.getAccountById(id);
    }

    @GetMapping("/list/{cin}")
    public List<AccountDTO> getAllAccountByCustomerId(@PathVariable String cin){
        return bankAccountService.getAllAccountByCustomerId(cin);
    }

    @GetMapping("/list")
    public List<BankAccountDTO> getAllBankAccount(){
        return bankAccountService.getAllBankAccount();
    }

    @GetMapping("/get/{id}")
    public BankAccountDTO getBankAccountById(@PathVariable String id) throws BankAccountNotFoundException{
        return bankAccountService.getBankAccountById(id);
    }

    @PostMapping("/save/current")
    CurrentAccountDTO saveCurrentBankAccount(@RequestBody SaveCurrentAccountDTO savedAccountDTO) throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(savedAccountDTO);
    }

    @PostMapping("/save/saving")
    public SavingAccountDTO saveSavingAccount(@RequestBody SaveSavingAccountDTO savingAccountDTO) throws CustomerNotFoundException{
        return bankAccountService.saveSavingAccount(savingAccountDTO);
    }

    @PutMapping("/update/current")
    public CurrentAccountDTO updateCurrentAccount(@RequestBody UpdateCurrentAccountDTO accountDTO) throws BankAccountNotFoundException, CommandRejectedException {
        return bankAccountService.updateCurrentAccount(accountDTO);
    }

    @PutMapping("/update/saving")
    public SavingAccountDTO updateSavingAccount(@RequestBody UpdateSavingAccountDTO savingAccountDTO) throws BankAccountNotFoundException, CommandRejectedException {
        return bankAccountService.updateSavingAccount(savingAccountDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
