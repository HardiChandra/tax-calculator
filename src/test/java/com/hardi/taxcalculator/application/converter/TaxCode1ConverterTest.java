package com.hardi.taxcalculator.application.converter;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode1Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeType;
import com.hardi.taxcalculator.domain.Transaction;
import org.junit.Test;

public class TaxCode1ConverterTest {
	private Converter<Transaction, TransactionResponse> taxCode1Converter = new TaxCode1Converter();
	
	@Test
	public void testTaxCode1Converter() {
		Transaction transaction = TransactionTestFactory.aTransaction(1);
		assert taxCode1Converter.isFor(transaction);
		
		TransactionResponse transactionResponse = taxCode1Converter.convert(transaction);
		assert transactionResponse.getName().equals(transaction.getName());
		assert transactionResponse.getTaxCode().equals(transaction.getTaxCode());
		assert transactionResponse.getType().equals(TaxCodeType.FOOD_AND_BEVERAGE.getType());
		assert transactionResponse.getRefundable().equals("Yes");
		assert transactionResponse.getPrice().equals(transaction.getPrice());
		assert transactionResponse.getTax().longValue() == 100L;
		assert transactionResponse.getAmount().longValue() == 1100L;
	}
}
