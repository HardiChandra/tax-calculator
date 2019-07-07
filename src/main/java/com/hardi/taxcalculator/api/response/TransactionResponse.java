package com.hardi.taxcalculator.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionResponse {
	private String name;
	private Integer taxCode;
	private String type;
	private boolean refundable;
	private BigDecimal price;
	private BigDecimal tax;
	private BigDecimal amount;
}
