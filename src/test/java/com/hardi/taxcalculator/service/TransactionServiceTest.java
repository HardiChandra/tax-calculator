package com.hardi.taxcalculator.service;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeConverterRegistry;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.repository.TransactionRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceTest {
	private TransactionRepository transactionRepositoryMock = Mockito.mock(TransactionRepository.class);
	private TaxCodeConverterRegistry taxCodeConverterRegistryMock = Mockito.mock(TaxCodeConverterRegistry.class);
	private TransactionService transactionService = new TransactionService(transactionRepositoryMock, taxCodeConverterRegistryMock);
	
	@Test
	public void testGetTransactions() {
		Mockito.when(transactionRepositoryMock.findAll())
				.thenReturn(TransactionTestFactory.multipleTransactions(3));
		List<TransactionResponse> transactionResponses = transactionService.getTransactions();
		Mockito.verify(transactionRepositoryMock, Mockito.times(1)).findAll();
		
		Mockito.verify(taxCodeConverterRegistryMock, Mockito.times(3)).convert(Mockito.any());
		assert transactionResponses.size() == 3;
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
	
	@Test(expected = TransactionService.TaxCodeNotSupportedException.class)
	public void testTaxCodeNotSupportedException() {
		CreateTransactionCommand createTransactionCommand = CreateTransactionCommand.builder()
				.name("test")
				.taxCode(0)
				.price(new BigDecimal(1000))
				.build();
		transactionService.createTransaction(createTransactionCommand);
	}
}
