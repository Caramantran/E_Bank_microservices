package com.glwa.accountservice.dtos;

import com.glwa.accountservice.enums.AccountStatus;

import java.math.BigDecimal;

public record UpdateCurrentAccountDTO(String id, BigDecimal balance, double overDraft, AccountStatus status) { }
