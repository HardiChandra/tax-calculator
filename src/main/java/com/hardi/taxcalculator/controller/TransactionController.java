package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.domain.Transaction;
import com.hardi.taxcalculator.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/v1/transaction")
@AllArgsConstructor
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = GET)
	public ResponseEntity<List<TransactionResponse>> getTransactions() {
		List<TransactionResponse> transactionResponses = transactionService.getTransactions();
		return new ResponseEntity<>(transactionResponses, HttpStatus.OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionCommand createTransactionCommand) {
		Transaction transaction = transactionService.createTransaction(createTransactionCommand);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}
}
