package com.hardi.taxcalculator.application.converter.taxcode;

import com.hardi.taxcalculator.domain.Transaction;

import java.math.BigDecimal;

public class TaxCode1Converter extends BaseTaxCodeConverter {
	
	@Override
	public boolean isFor(Transaction transaction) {
		return transaction.getTaxCode() == 1;
	}

	@Override
	protected String getTaxCodeType() {
		return TaxCodeType.FOOD_AND_BEVERAGE.getType();
	}

	@Override
	protected boolean getRefundable() {
		return true;
	}

	@Override
	protected BigDecimal getTaxPercentage() {
		return new BigDecimal(10);
	}
}
