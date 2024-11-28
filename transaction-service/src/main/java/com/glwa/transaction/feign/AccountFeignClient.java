package com.glwa.transaction.feign;

import com.glwa.transaction.feign.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountFeignClient {
    @GetMapping("/account/get/{id}")
    BankAccountDTO getById(@PathVariable String id);

    @GetMapping("/account/find/{id}")
    AccountDTO findById(@PathVariable String id);

    @PutMapping("/account/update/current")
    CurrentAccountDTO updateCurrentAccount(@RequestBody UpdateCurrentAccountDTO accountDTO);

    @PutMapping("/account/update/saving")
    SavingAccountDTO updateSavingAccount(@RequestBody UpdateSavingAccountDTO savingAccountDTO);
}
