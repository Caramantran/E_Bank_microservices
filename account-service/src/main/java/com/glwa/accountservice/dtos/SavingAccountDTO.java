package com.glwa.accountservice.dtos;

import com.glwa.accountservice.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.Date;

public class SavingAccountDTO  extends BankAccountDTO {
    private String id;
    private BigDecimal balance;
    private Date createdAt;
    private AccountStatus status;
    private String customerId;
    private double interestRate;
    private String rib;

    public SavingAccountDTO() {
        super();
    }

    public SavingAccountDTO(String id, BigDecimal balance, Date createdAt, AccountStatus status, String customerId, double interestRate, String rib) {
        this.id = id;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
        this.customerId = customerId;
        this.interestRate = interestRate;
        this.rib = rib;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
