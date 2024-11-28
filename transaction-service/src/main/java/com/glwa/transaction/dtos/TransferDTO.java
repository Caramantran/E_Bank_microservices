package com.glwa.transaction.dtos;

import java.math.BigDecimal;

public record TransferDTO(String accountSource, String accountDestination, BigDecimal amount, String motive) { }
