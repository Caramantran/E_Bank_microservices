package com.glwa.transaction.feign.dtos;

import com.glwa.transaction.enums.AccountStatus;

import java.math.BigDecimal;

public record UpdateCurrentAccountDTO(String id, BigDecimal balance, double overDraft, AccountStatus status) { }
