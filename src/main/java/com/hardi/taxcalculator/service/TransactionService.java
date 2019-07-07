package com.hardi.taxcalculator.service;

import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeConverterRegistry;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TaxCodeConverterRegistry taxCodeConverterRegistry;
	
	public List<TransactionResponse> getTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream()
				.map(taxCodeConverterRegistry::convert)
				.collect(Collectors.toList());
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
