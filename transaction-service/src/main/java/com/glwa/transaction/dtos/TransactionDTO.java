package com.glwa.transaction.dtos;

import com.glwa.transaction.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;

public record TransactionDTO(String id, Date date, BigDecimal amount, OperationType type, String description) { }
