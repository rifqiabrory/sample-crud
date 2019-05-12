package com.kantin.dao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class Currency {
	private DecimalFormat localCurrency = (DecimalFormat) DecimalFormat.getCurrencyInstance();
	private DecimalFormatSymbols formatSymbol = new DecimalFormatSymbols();
	
	public Currency() {
		formatSymbol.setCurrencySymbol("Rp. ");
		formatSymbol.setMonetaryDecimalSeparator(',');
		formatSymbol.setGroupingSeparator(',');
	}
	
	public String getFormat(double value) {
		localCurrency.setDecimalFormatSymbols(formatSymbol);
		return localCurrency.format(value);
	}
	
	public String getParsing(String value) {
		String result = null;
		try {
			localCurrency.setDecimalFormatSymbols(formatSymbol);
			Number number = localCurrency.parse(value);
			double db = number.doubleValue();
			result = String.format("%.2f", db);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
