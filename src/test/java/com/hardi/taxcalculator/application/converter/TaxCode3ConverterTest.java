package com.hardi.taxcalculator.application.converter;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode2Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode3Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeType;
import com.hardi.taxcalculator.domain.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

public class TaxCode3ConverterTest {
	private Converter<Transaction, TransactionResponse> taxCode3Converter = new TaxCode3Converter();
	
	@Test
	public void testTaxCode1Converter() {
		Transaction transaction = TransactionTestFactory.aTransaction(2);
		assert taxCode3Converter.isFor(transaction);
		
		TransactionResponse transactionResponse = taxCode3Converter.convert(transaction);
		assert transactionResponse.getName().equals(transaction.getName());
		assert transactionResponse.getTaxCode().equals(transaction.getTaxCode());
		assert transactionResponse.getType().equals(TaxCodeType.ENTERTAINMENT.getType());
		assert transactionResponse.getRefundable().equals("No");
		assert transactionResponse.getPrice().equals(transaction.getPrice());
		assert transactionResponse.getTax().longValue() == 9L;
		assert transactionResponse.getAmount().longValue() == 1009L;
		
		transaction = TransactionTestFactory.aTransaction(2, new BigDecimal(50));
		transactionResponse = taxCode3Converter.convert(transaction);
		assert transactionResponse.getPrice().equals(transaction.getPrice());
		assert transactionResponse.getTax().longValue() == 0L;
		assert transactionResponse.getAmount().longValue() == 50L;
	}
}
