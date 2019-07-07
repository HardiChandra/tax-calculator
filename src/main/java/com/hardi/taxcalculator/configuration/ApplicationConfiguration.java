package com.hardi.taxcalculator.configuration;

import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode1Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode2Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCode3Converter;
import com.hardi.taxcalculator.application.converter.taxcode.TaxCodeConverterRegistry;
import com.hardi.taxcalculator.domain.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public TaxCodeConverterRegistry taxCodeConverterRegistry() {
		List<Converter<Transaction, TransactionResponse>> taxCodeConverters = new ArrayList<>();
		taxCodeConverters.add(new TaxCode1Converter());
		taxCodeConverters.add(new TaxCode2Converter());
		taxCodeConverters.add(new TaxCode3Converter());
		return new TaxCodeConverterRegistry(taxCodeConverters);
	}
}
