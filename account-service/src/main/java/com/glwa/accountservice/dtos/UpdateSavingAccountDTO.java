package com.glwa.accountservice.dtos;

import com.glwa.accountservice.enums.AccountStatus;

import java.math.BigDecimal;

public record UpdateSavingAccountDTO(String id, BigDecimal balance, double interestRate, AccountStatus status) { }
