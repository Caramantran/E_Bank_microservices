package com.glwa.transaction.dtos;

import java.math.BigDecimal;

public record DebitDTO(String accountId, BigDecimal amount, String motive) { }
