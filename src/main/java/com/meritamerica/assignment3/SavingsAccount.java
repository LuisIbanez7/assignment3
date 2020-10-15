package com.meritamerica.assignment3;

import java.text.ParseException;
import java.util.Date;

/*
 * This is the definition of the SavingsAccount Class.
 * This class simulates the savings account of an account holder.
 */
public class SavingsAccount extends BankAccount {
	
	private double interestRate = 0.01;
	
	public SavingsAccount() {
		super();
	}
//	public SavingsAccount(double openingBalance) {
//		super(openingBalance);
//	}
	
	public SavingsAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public SavingsAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}
	
	public SavingsAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	private static SavingsAccount readFromString(String accountData) throws ParseException {
		// § Should throw a java.lang.NumberFormatException if String cannot be
		// correctly parsed
		SavingsAccount saTemp = new SavingsAccount();
		try {
			Double.parseDouble(accountData);
			return saTemp;
		} catch (NumberFormatException e) {
			throw e;
		}

	}
}