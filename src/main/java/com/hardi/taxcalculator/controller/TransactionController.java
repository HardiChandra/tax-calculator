package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.command.CreateTransactionCommand;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/v1/transaction")
@AllArgsConstructor
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = GET)
	public ResponseEntity<List<Transaction>> getTransactions() {
		List<Transaction> transactions = transactionService.getTransactions();
		return new ResponseEntity<>(transactions, HttpStatus.NOT_IMPLEMENTED);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<Transaction> createTransaction(CreateTransactionCommand createTransactionCommand) {
		Transaction transaction = transactionService.createTransaction(createTransactionCommand);
		return new ResponseEntity<>(transaction, HttpStatus.NOT_IMPLEMENTED);
	}
}
