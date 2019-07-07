package com.hardi.taxcalculator.repository;

import com.hardi.taxcalculator.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
