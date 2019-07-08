package com.hardi.taxcalculator.application.converter.taxcode;

import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.Converter;
import com.hardi.taxcalculator.domain.Transaction;

import java.util.List;

public class TaxCodeConverterRegistry {
	private List<Converter<Transaction, TransactionResponse>> taxCodeConverters;
	
	public TaxCodeConverterRegistry(List<Converter<Transaction, TransactionResponse>> taxCodeConverters) {
		this.taxCodeConverters = taxCodeConverters;
	}
	
	public TransactionResponse convert(Transaction transaction) {
		Converter<Transaction, TransactionResponse> converter = this.taxCodeConverters.stream()
				.filter(taxCodeConverter -> taxCodeConverter.isFor(transaction))
				.findFirst()
				.orElse(null);
		if (converter != null) {
			return converter.convert(transaction);
		} else {
			throw new TaxCodeConverterNotFoundException();
		}
	}
	
	public class TaxCodeConverterNotFoundException extends RuntimeException {
		TaxCodeConverterNotFoundException() {
			super("Tax Code Converter not found");
		}
	}
}
