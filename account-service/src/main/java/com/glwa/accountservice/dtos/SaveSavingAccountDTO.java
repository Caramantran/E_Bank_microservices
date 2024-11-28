package com.glwa.accountservice.dtos;

import java.math.BigDecimal;

public record SaveSavingAccountDTO(BigDecimal initialBalance, double interestRate, String customerId) { }
