package com.glwa.transaction.dtos;

import java.math.BigDecimal;

public record CreditDTO(String accountId, BigDecimal amount, String motive) { }
