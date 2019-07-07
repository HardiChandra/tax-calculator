package com.hardi.taxcalculator.controller;

import com.hardi.taxcalculator.command.CreateTransactionCommand;
import com.hardi.taxcalculator.domain.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/v1/transaction")
public class TransactionController {
	@RequestMapping(method = GET)
	public ResponseEntity<List<Transaction>> getTransactions() {
		//TODO: Implement Service
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<Transaction>> createTransaction(CreateTransactionCommand createTransactionCommand) {
		//TODO: Implement Service
		return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
	}
}
