package com.glwa.transaction.dtos;

import java.math.BigDecimal;
import java.util.List;

public record AccountHistoryDTO(String accountId, BigDecimal balance,
                                int currentPage, int totalPages,
                                int pageSize, List<TransactionDTO> operations) { }
