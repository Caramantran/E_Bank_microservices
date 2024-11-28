package com.glwa.transaction.feign.dtos;

import com.glwa.transaction.enums.AccountStatus;

import java.math.BigDecimal;

public record UpdateSavingAccountDTO(String id, BigDecimal balance, double interestRate, AccountStatus status) { }
