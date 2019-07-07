package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.command.CreateTransactionCommand;
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
		List<Transaction> transactionsMock = buildTransactionsMock();
		Mockito.when(transactionService.getTransactions()).thenReturn(transactionsMock);
		
		ResponseEntity<List<Transaction>> transactionsResponse = transactionController.getTransactions();
		Mockito.verify(transactionService, Mockito.times(1)).getTransactions();
		assert transactionsResponse.getBody() == transactionsMock;
	}

	@Test
	public void testCreateTransaction() {
		Transaction transactionMock = buildTransactionMock();
		CreateTransactionCommand createTransactionCommand = buildCreateTransactionCommandMock();
		Mockito.when(transactionService.createTransaction(createTransactionCommand)).thenReturn(transactionMock);

		ResponseEntity<Transaction> transactionsResponse = transactionController.createTransaction(createTransactionCommand);
		Mockito.verify(transactionService, Mockito.times(1)).createTransaction(createTransactionCommand);
		assert transactionsResponse.getBody() == transactionMock;
	}

	private CreateTransactionCommand buildCreateTransactionCommandMock() {
		return CreateTransactionCommand.builder().name("test1").taxCode(1).price(new BigDecimal(1000)).build();
	}

	private Transaction buildTransactionMock() {
		return Transaction.builder().id(1L).name("test1").taxCode(1).price(new BigDecimal(1000)).build();
	}

	private List<Transaction> buildTransactionsMock() {
		List<Transaction> transactionsMock = new ArrayList<>();
		transactionsMock.add(Transaction.builder().id(1L).name("test1").taxCode(1).price(new BigDecimal(1000)).build());
		transactionsMock.add(Transaction.builder().id(2L).name("test2").taxCode(2).price(new BigDecimal(2000)).build());
		return transactionsMock;
	}
}
