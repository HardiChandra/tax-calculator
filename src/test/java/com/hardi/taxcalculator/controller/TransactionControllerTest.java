package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.service.TransactionService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TransactionControllerTest {
	private TransactionService transactionService = Mockito.mock(TransactionService.class);
	private TransactionController transactionController = new TransactionController(transactionService);
	
	@Test
	public void testGetTransactions() {
		List<TransactionResponse> transactionResponsesMock = TransactionTestFactory.multipleTransactionResponses(3);
		Mockito.when(transactionService.getTransactions()).thenReturn(transactionResponsesMock);
		
		ResponseEntity<List<TransactionResponse>> transactionsResponse = transactionController.getTransactions();
		Mockito.verify(transactionService, Mockito.times(1)).getTransactions();
		assert transactionsResponse.getBody() == transactionResponsesMock;
	}

	@Test
	public void testCreateTransaction() {
		TransactionResponse transactionResponseMock = TransactionTestFactory.aTransactionResponse(1);
		CreateTransactionCommand createTransactionCommand = TransactionTestFactory.aCreateTransactionCommand();
		Mockito.when(transactionService.createTransaction(createTransactionCommand)).thenReturn(transactionResponseMock);

		ResponseEntity<TransactionResponse> transactionsResponse = transactionController.createTransaction(createTransactionCommand);
		Mockito.verify(transactionService, Mockito.times(1)).createTransaction(createTransactionCommand);
		assert transactionsResponse.getBody() == transactionResponseMock;
	}
}
