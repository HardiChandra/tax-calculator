package com.hardi.taxcalculator.application.converter.taxcode;

import com.hardi.taxcalculator.domain.Transaction;

import java.math.BigDecimal;

public class TaxCode3Converter extends BaseTaxCodeConverter {
	@Override
	public boolean isFor(Transaction transaction) {
		return transaction.getTaxCode() == 2;
	}

	@Override
	protected String getTaxCodeType() {
		return TaxCodeType.ENTERTAINMENT.getType();
	}

	@Override
	protected boolean getRefundable() {
		return false;
	}

	@Override
	protected BigDecimal getTaxPercentage() {
		return this.transaction.getPrice().longValue() > 0L && this.transaction.getPrice().longValue() < 100L ? BigDecimal.ZERO : BigDecimal.ONE;
	}
	
	@Override
	protected BigDecimal getTaxablePrice() {
		return this.transaction.getPrice().subtract(new BigDecimal(100));
	}
}
