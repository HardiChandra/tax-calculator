package com.hardi.taxcalculator.service;

import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}
	
	public Transaction createTransaction(CreateTransactionCommand createTransactionCommand) {
		Transaction transaction = createNewTransaction(createTransactionCommand);
		return transactionRepository.save(transaction);
	}

	Transaction createNewTransaction(CreateTransactionCommand createTransactionCommand) {
		return Transaction.builder()
				.name(createTransactionCommand.getName())
				.taxCode(createTransactionCommand.getTaxCode())
				.price(createTransactionCommand.getPrice())
				.build();
	}
}
