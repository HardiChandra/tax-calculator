package com.hardi.taxcalculator.service;

import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.repository.TransactionRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class TransactionServiceTest {
	private TransactionRepository transactionRepositoryMock = Mockito.mock(TransactionRepository.class);
	private TransactionService transactionService = new TransactionService(transactionRepositoryMock);
	
	@Test
	public void testGetTransactions() {
		transactionService.getTransactions();
		Mockito.verify(transactionRepositoryMock, Mockito.times(1)).findAll();
	}

	@Test
	public void testCreateNewTransaction() {
		String name = "test";
		Integer taxCode = 1;
		BigDecimal price = new BigDecimal(1000);
		CreateTransactionCommand createTransactionCommand = CreateTransactionCommand.builder()
				.name(name)
				.taxCode(taxCode)
				.price(price)
				.build();
		ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
		transactionService.createTransaction(createTransactionCommand);
		Mockito.verify(transactionRepositoryMock, Mockito.times(1)).save(captor.capture());
		assert captor.getValue().getName().equals(name);
		assert captor.getValue().getTaxCode().equals(taxCode);
		assert captor.getValue().getPrice().equals(price);
	}
}
