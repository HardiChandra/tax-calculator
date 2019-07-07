package com.hardi.taxcalculator.application.converter;

import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode1Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode2Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeType;
import com.hardi.taxcalculator.domain.Transaction;
import org.junit.Test;

public class TaxCode2ConverterTest {
	private Converter<Transaction, TransactionResponse> taxCode2Converter = new TaxCode2Converter();
	
	@Test
	public void testTaxCode1Converter() {
		Transaction transaction = TransactionTestFactory.aTransaction(2);
		assert taxCode2Converter.isFor(transaction);
		
		TransactionResponse transactionResponse = taxCode2Converter.convert(transaction);
		assert transactionResponse.getName().equals(transaction.getName());
		assert transactionResponse.getTaxCode().equals(transaction.getTaxCode());
		assert transactionResponse.getType().equals(TaxCodeType.TOBACCO.getType());
		assert transactionResponse.getRefundable().equals("No");
		assert transactionResponse.getPrice().equals(transaction.getPrice());
		assert transactionResponse.getTax().longValue() == 30L;
		assert transactionResponse.getAmount().longValue() == 1030L;
	}
}
