package com.hardi.taxcalculator.application.converter.taxcode;

import com.hardi.taxcalculator.domain.Transaction;

import java.math.BigDecimal;

public class TaxCode2Converter extends BaseTaxCodeConverter {

	@Override
	public boolean isFor(Transaction transaction) {
		return transaction.getTaxCode() == 2;
	}

	@Override
	protected String getTaxCodeType() {
		return TaxCodeType.TOBACCO.getType();
	}

	@Override
	protected boolean getRefundable() {
		return false;
	}

	@Override
	protected BigDecimal getTaxPercentage() {
		return new BigDecimal(2);
	}
	
	@Override
	protected BigDecimal getBaseTax() {
		return new BigDecimal(10);
	}
}
