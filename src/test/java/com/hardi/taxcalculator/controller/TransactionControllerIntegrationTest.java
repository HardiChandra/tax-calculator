package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeType;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.repository.TransactionRepository;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class TransactionControllerIntegrationTest {
	@Autowired
	TransactionController transactionController;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	Flyway flyway;
	
	@Before
	public void init() {
		flyway.migrate();
	}
	
	@After
	public void finalize() {
		flyway.clean();
	}

	@Test
	public void contexLoads() {
		assert transactionController != null;
	}
	
	@Test
	public void testCreateNewTransaction() {
		CreateTransactionCommand createTransactionCommand = TransactionTestFactory.aCreateTransactionCommand();
		ResponseEntity<TransactionResponse> transactionResponse = transactionController.createTransaction(createTransactionCommand);
		assert transactionResponse.getStatusCode() == HttpStatus.CREATED;

		List<Transaction> transactions = transactionRepository.findAll();
		assert transactions.size() == 1;
		assert transactions.get(0).getName().equals(createTransactionCommand.getName());
		assert transactionResponse.getBody().getName().equals(createTransactionCommand.getName());
	}
	
	@Test
	public void testGetTransactions() {
		transactionController.createTransaction(TransactionTestFactory.aCreateTransactionCommand());
		transactionController.createTransaction(TransactionTestFactory.aCreateTransactionCommand());
		transactionController.createTransaction(TransactionTestFactory.aCreateTransactionCommand());

		ResponseEntity<List<TransactionResponse>> transactionResponses = transactionController.getTransactions();
		assert transactionResponses.getBody().size() == 3;
		TransactionResponse transactionResponse = transactionResponses.getBody().get(0);
		assert transactionResponse.getName().equals(TransactionTestFactory.aCreateTransactionCommand().getName());
		assert transactionResponse.getTaxCode() == 1;
		assert transactionResponse.getType().equals(TaxCodeType.FOOD_AND_BEVERAGE.getType());
		assert transactionResponse.getRefundable().equals("Yes");
		assert transactionResponse.getPrice().longValue() == 1000L;
		assert transactionResponse.getTax().longValue() == 100L;
		assert transactionResponse.getAmount().longValue() == 1100L;
	}
}
