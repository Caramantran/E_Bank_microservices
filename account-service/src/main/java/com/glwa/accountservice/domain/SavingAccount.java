package com.glwa.accountservice.domain;

import com.glwa.accountservice.enums.AccountStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount {

    private double interestRate;

    public SavingAccount(String id, BigDecimal balance, Date createdAt, AccountStatus status, String customerId, double interestRate, String rib) {
        super(id, balance, createdAt, status, customerId, rib);
        this.interestRate = interestRate;
    }

    public SavingAccount() {
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
