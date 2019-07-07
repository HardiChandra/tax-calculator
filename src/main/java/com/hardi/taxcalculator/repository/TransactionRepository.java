package com.hardi.taxcalculator.repository;

import com.hardi.taxcalculator.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {}
