package com.glwa.transaction.domain;

import com.glwa.transaction.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date date;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private String description;
    @Column(nullable = false)
    private String bankAccountId;
}
