package com.hardi.taxcalculator.application.converter.taxcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TaxCodeType {
	FOOD_AND_BEVERAGE("Food & Beverage"),
	TOBACCO("Tobacco"),
	ENTERTAINMENT("Entertainment");
	
	@Getter
	private String type;
}
