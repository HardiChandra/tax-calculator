package com.hardi.taxcalculator.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class CreateTransactionCommand {
	private String name;
	private Integer taxCode;
	private BigDecimal price;
}
