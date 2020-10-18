package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

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
	public static SavingsAccount readFromString(String accountData) throws ParseException {
		// § Should throw a java.lang.NumberFormatException if String cannot be
		// correctly parsed
		String[] dataSplit = accountData.split(",");
		try {
//			for(String val : dataSplit) {
//				Double.parseDouble(val);
//			}
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
			long accountNumber = Long.parseLong(dataSplit[0]);
			double balance = Double.parseDouble(dataSplit[1]);
			double interestRate = Double.parseDouble(dataSplit[2]);
			Date accountOpened = dateFormatter.parse(dataSplit[3]);
			
			SavingsAccount saTemp = new SavingsAccount(accountNumber, balance, interestRate, accountOpened);
			return saTemp;
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