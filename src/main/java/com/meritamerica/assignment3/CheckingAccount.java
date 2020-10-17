package com.meritamerica.assignment3;

import java.text.ParseException;
import java.util.Date;

/*
 * This is the definition of the CheckingAccount class.
 * This class simulates the checking account of an account holder
 * within a banking application.
 */
public class CheckingAccount extends BankAccount {
	
	private double interestRate = 0.0001;
	
	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public CheckingAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	private static CheckingAccount readFromString(String accountData) throws ParseException {
		// § Should throw a java.lang.NumberFormatException if String cannot be
		// correctly parsed
		CheckingAccount baTemp = new CheckingAccount();
		try {
			Double.parseDouble(accountData);
			return baTemp;
		} catch (NumberFormatException e) {
			throw e;
		}

	}
	
	public boolean withdraw (double amount) {
		if (this.getBalance()>= amount) {
			double newBalance = this.getBalance()- amount;
			this.setBalance(newBalance);
			return true;
		}
		return false;
	}
	
	public boolean deposit (double amount) {
		if (amount < 0) {
			return false;
		}
		double newBalance = this.getBalance()+ amount;
		this.setBalance(newBalance);
		return true;
	}

}