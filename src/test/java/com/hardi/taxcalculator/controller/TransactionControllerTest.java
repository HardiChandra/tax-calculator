package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.service.TransactionService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionControllerTest {
	private TransactionService transactionService = Mockito.mock(TransactionService.class);
	private TransactionController transactionController = new TransactionController(transactionService);
	
	@Test
	public void testGetTransactions() {
		List<Transaction> transactionsMock = TransactionTestFactory.multipleTransactions(3);
		Mockito.when(transactionService.getTransactions()).thenReturn(transactionsMock);
		
		ResponseEntity<List<Transaction>> transactionsResponse = transactionController.getTransactions();
		Mockito.verify(transactionService, Mockito.times(1)).getTransactions();
		assert transactionsResponse.getBody() == transactionsMock;
	}

	@Test
	public void testCreateTransaction() {
		Transaction transactionMock = TransactionTestFactory.aTransaction();
		CreateTransactionCommand createTransactionCommand = buildCreateTransactionCommandMock();
		Mockito.when(transactionService.createTransaction(createTransactionCommand)).thenReturn(transactionMock);

		ResponseEntity<Transaction> transactionsResponse = transactionController.createTransaction(createTransactionCommand);
		Mockito.verify(transactionService, Mockito.times(1)).createTransaction(createTransactionCommand);
		assert transactionsResponse.getBody() == transactionMock;
	}

	private CreateTransactionCommand buildCreateTransactionCommandMock() {
		return CreateTransactionCommand.builder().name("test1").taxCode(1).price(new BigDecimal(1000)).build();
	}
}
