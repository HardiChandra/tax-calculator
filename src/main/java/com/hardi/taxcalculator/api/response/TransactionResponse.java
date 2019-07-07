package com.hardi.taxcalculator.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class TransactionResponse {
	private String name;
	private Integer taxCode;
	private String type;
	private String refundable;
	private BigDecimal price;
	private BigDecimal tax;
	private BigDecimal amount;
}
