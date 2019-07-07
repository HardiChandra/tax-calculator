package com.hardi.taxcalculator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Transaction {
	@Id
	Long id;
	String name;
	Integer taxCode;
	BigDecimal price;
}
