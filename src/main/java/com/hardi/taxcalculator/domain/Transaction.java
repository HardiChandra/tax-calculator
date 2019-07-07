package com.hardi.taxcalculator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Builder
public class Transaction {
	@Id
	private Long id;
	private String name;
	private Integer taxCode;
	private BigDecimal price;
}
