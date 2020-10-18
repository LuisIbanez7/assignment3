package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static CheckingAccount readFromString(String accountData) throws ParseException {
		try {
			String[] dataSplit = accountData.split(",");
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			long accountNumber = Long.parseLong(dataSplit[0]);
			double balance = Double.parseDouble(dataSplit[1]);
			double interestRate = Double.parseDouble(dataSplit[2]);
			Date accountOpened = dateFormatter.parse(dataSplit[3]);
			
			CheckingAccount caTemp = new CheckingAccount(accountNumber, balance, interestRate, accountOpened);
			return caTemp;
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