package com.hardi.taxcalculator;

import com.hardi.taxcalculator.api.command.CreateTransactionCommand;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.domain.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionTestFactory {
	public static Transaction aTransaction() {
		return aTransaction(1);
	}

	public static Transaction aTransaction(Integer taxCode) {
		return aTransaction(taxCode, new BigDecimal(1000));
	}

	public static Transaction aTransaction(Integer taxCode, BigDecimal price) {
		return Transaction.builder().id(1L).name("test1").taxCode(taxCode).price(price).build();
	}

	public static List<Transaction> multipleTransactions(Integer count) {
		List<Transaction> transactions = new ArrayList<>();
		for (Integer i=1 ; i <= count ; i++) {
			transactions.add(Transaction.builder().id(i.longValue()).name("test" + i).taxCode(i).price(new BigDecimal(i * 1000)).build());
		}
		return transactions;
	}

	public static List<TransactionResponse> multipleTransactionResponses(Integer count) {
		List<TransactionResponse> transactionResponses = new ArrayList<>();
		for (Integer i=1 ; i <= count ; i++) {
			transactionResponses.add(aTransactionResponse(i));
		}
		return transactionResponses;
	}
	
	public static TransactionResponse aTransactionResponse(Integer number) {
		return TransactionResponse.builder().name("test" + number).taxCode(number).price(new BigDecimal(number * 1000)).build();
	}
	
	public static CreateTransactionCommand aCreateTransactionCommand() {
		return CreateTransactionCommand.builder().name("test1").taxCode(1).price(new BigDecimal(1000)).build();
	}
}
