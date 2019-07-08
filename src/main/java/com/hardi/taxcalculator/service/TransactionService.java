package com.hardi.taxcalculator.service;

import com.google.common.collect.ImmutableList;
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
	
	private static final List<Integer> acceptableTaxCodes = ImmutableList.of(1, 2, 3);
	
	public List<TransactionResponse> getTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream()
				.map(taxCodeConverterRegistry::convert)
				.collect(Collectors.toList());
	}
	
	public TransactionResponse createTransaction(CreateTransactionCommand createTransactionCommand) {
		if (!acceptableTaxCodes.contains(createTransactionCommand.getTaxCode())) {
			throw new TaxCodeNotSupportedException();
		}
		Transaction transaction = createNewTransaction(createTransactionCommand);
		Transaction transactionAfterSave = transactionRepository.save(transaction);
		return taxCodeConverterRegistry.convert(transactionAfterSave);
	}

	Transaction createNewTransaction(CreateTransactionCommand createTransactionCommand) {
		return Transaction.builder()
				.name(createTransactionCommand.getName())
				.taxCode(createTransactionCommand.getTaxCode())
				.price(createTransactionCommand.getPrice())
				.build();
	}
	
	public class TaxCodeNotSupportedException extends RuntimeException {
		TaxCodeNotSupportedException() {
			super("Tax Code Not Supported");
		}
	}
}
