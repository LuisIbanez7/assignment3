package com.meritamerica.assignment3;

import java.util.Date;

public class CDOffering {

	private int term;
	private double interestRate;
	
	public CDOffering()	{
		
	}
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public static CDOffering readFromString(String cdOfferingDataString) {
		//CDOffering cdoTemp = new CDOffering();
		try {
			String[] dataSplit = cdOfferingDataString.split(",");
			int term = Integer.parseInt(dataSplit[0]);
			double interestRate = Double.parseDouble(dataSplit[1]);
			CDOffering cdoTemp = new CDOffering(term,interestRate);
			return cdoTemp;
		} catch (NumberFormatException e) {
			throw e;
		}

	}
	//Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
	
	public String writeToString() {
		return null;
		
	}

	public int getTerm() {
		return term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	
}
