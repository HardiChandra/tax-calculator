package com.hardi.taxcalculator.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionCommand {
	@NotNull
	private String name;
	@NotNull
	private Integer taxCode;
	@NotNull
	private BigDecimal price;
}
