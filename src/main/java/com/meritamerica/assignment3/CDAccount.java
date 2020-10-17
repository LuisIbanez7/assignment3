package com.meritamerica.assignment3;

import java.text.ParseException;
import java.util.Date;

public class CDAccount extends BankAccount {

	private CDOffering offering;
	private double balance;
	private Date startDate;

	public CDAccount() {
		super();
	}
//	public CDAccount(double openingBalance) {
//		super(openingBalance);
//	}

	public CDAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}

	public CDAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}

	public CDAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}

	private static CDAccount readFromString(String accountData) throws ParseException {
		// § Should throw a java.lang.NumberFormatException if String cannot be
		// correctly parsed
		CDAccount saTemp = new CDAccount();
		try {
			Double.parseDouble(accountData);
			return saTemp;
		} catch (NumberFormatException e) {
			throw e;
		}

	}

	@Override
	public boolean withdraw(double amount) {
		if (this.getBalance() >= amount) {
			double newBalance = this.getBalance() - amount;
			this.setBalance(newBalance);
			return true;
		}
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		if (amount < 0) {
			return false;
		}
		double newBalance = this.getBalance() + amount;
		this.setBalance(newBalance);
		return true;
	}

	@Override
	public String toString() {
		return generateStringForToString(getTerm());
	}

//	public CDAccount(CDOffering offering, double balance) {
//		this.startDate = new Date();
//		
//		this.offering = offering;
//		this.balance = balance;
//	}
//	
//	public double getBalance() {
//		return balance;
//	}
//	
//	public double getInterestRate() {
//		return offering.getInterestRate();
//	}
//	
	public int getTerm() {
		return offering.getTerm();
	}
//	
//	public Date getStartDate() {
//		return startDate;
//	}
//	
//	public long getAccountNumber() {
//		return super.getAccountNumber();
//	}
//	
//	public double futureValue() {
//		double interestRate = offering.getInterestRate();
//		int years = offering.getTerm();
//		
//		return balance * (Math.pow(1 + interestRate, years));
//	}

}
