package com.hardi.taxcalculator.application.converter.taxcode;

import com.hardi.taxcalculator.api.response.TransactionResponse;
import com.hardi.taxcalculator.application.converter.Converter;
import com.hardi.taxcalculator.domain.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BaseTaxCodeConverter implements Converter<Transaction, TransactionResponse> {
	protected Transaction transaction;
	
	@Override
	public abstract boolean isFor(Transaction transaction);

	@Override
	public TransactionResponse convert(Transaction transaction) {
		this.transaction = transaction;
		BigDecimal price = transaction.getPrice();
		BigDecimal tax = getBaseTax().add(getTaxablePrice().multiply(getTaxPercentage().divide(new BigDecimal(100))));
		return TransactionResponse.builder()
				.name(transaction.getName())
				.taxCode(transaction.getTaxCode())
				.type(getTaxCodeType())
				.refundable(getRefundable() ? "Yes" : "No")
				.price(price)
				.tax(tax)
				.amount(price.add(tax))
				.build();
	}

	protected BigDecimal getBaseTax() {
		return new BigDecimal(0);
	}

	protected BigDecimal getTaxablePrice() {
		return this.transaction.getPrice();
	}

	protected abstract String getTaxCodeType();

	protected abstract boolean getRefundable();

	protected abstract BigDecimal getTaxPercentage();
}
