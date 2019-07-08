package com.hardi.taxcalculator.application.converter;

import com.google.common.collect.ImmutableList;
import com.hardi.taxcalculator.TransactionTestFactory;
import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode1Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode2Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode3Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeConverterRegistry;
import com.hardi.taxcalculator.domain.Transaction;
import org.junit.Test;

import java.util.List;

public class TaxCodeConverterRegistryTest {
	private List<Converter<Transaction, TransactionResponse>> taxCodeConverters = ImmutableList.of(new TaxCode1Converter(), new TaxCode2Converter(), new TaxCode3Converter());
	private TaxCodeConverterRegistry taxCodeConverterRegistry = new TaxCodeConverterRegistry(taxCodeConverters);
	
	@Test
	public void testConvert() {
		Transaction transaction = TransactionTestFactory.aTransaction();
		TransactionResponse transactionResponse = taxCodeConverterRegistry.convert(transaction);
		assert transactionResponse.getName().equals(transaction.getName());
	}

	@Test(expected = TaxCodeConverterRegistry.TaxCodeConverterNotFoundException.class)
	public void testConverterNotFound() {
		Transaction transaction = TransactionTestFactory.aTransaction();
		transaction.setTaxCode(5);
		taxCodeConverterRegistry.convert(transaction);
	}
}
