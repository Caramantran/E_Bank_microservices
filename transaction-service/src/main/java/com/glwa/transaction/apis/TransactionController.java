package com.glwa.transaction.apis;

import com.glwa.transaction.exceptions.BalanceNotSufficientException;
import com.glwa.transaction.exceptions.BankAccountNotFoundException;
import com.glwa.transaction.exceptions.OperationNotFoundException;
import com.glwa.transaction.services.TransactionService;
import com.glwa.transaction.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService operationService;

    public TransactionController(TransactionService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{
        return operationService.debit(debitDTO);
    }

    @PostMapping("/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException{
        return operationService.credit(creditDTO);
    }

    @PostMapping("/transfer")
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{
        return operationService.transfer(transferDTO);
    }

    @GetMapping("/list/{accountId}")
    public List<TransactionDTO> getAllOperationsByAccountId(@PathVariable String accountId){
        return operationService.getAllOperationsByAccountId(accountId);
    }

    @GetMapping("/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name ="page", defaultValue = "0") int page,
                                               @RequestParam(name ="size", defaultValue = "5") int size) throws BankAccountNotFoundException{
        return operationService.getAccountHistory(accountId, page, size);
    }

    @GetMapping("/get/{id}")
    public TransactionDTO getOperationById(@PathVariable Long id) throws OperationNotFoundException {
        return operationService.getOperationById(id);
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
