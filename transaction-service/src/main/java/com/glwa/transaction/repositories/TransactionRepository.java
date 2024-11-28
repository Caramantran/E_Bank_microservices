package com.glwa.transaction.repositories;

import com.glwa.transaction.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.bankAccountId =?1")
    List<Transaction> findByBankAccountId(String accountId);

    Page<Transaction> findByBankAccountIdOrderByDateDesc(String accountId, Pageable pageable);
}
