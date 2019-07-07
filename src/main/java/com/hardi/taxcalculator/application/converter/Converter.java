package com.hardi.taxcalculator.application.converter;

public interface Converter<S, T> {
	public boolean isFor(S s);
	public T convert(S s);
}
