package com.glwa.accountservice.repositories;

import com.glwa.accountservice.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findAllByCustomerId(String customerId);
}
