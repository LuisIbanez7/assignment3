package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	public CDAccount(CDOffering offering, double balance) {
		this.startDate = new Date();

		this.offering = offering;
		this.balance = balance;
	}

	public CDAccount(CDOffering offering, double interestRate, long accountNumber, double balance,
			Date accountOpenedOn) {
		// this.startDate = new Date();
		super(accountNumber, balance, interestRate, accountOpenedOn);

		this.offering = offering;
		this.balance = balance;
	}

	public CDAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}

	public CDAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}

	public CDAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}

	public static CDAccount readFromString(String accountData) throws ParseException {
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
			int term = Integer.parseInt(dataSplit[4]);
//CDOffering offering, double interestRate, long accountNumber, double balance, Date accountOpenedOn			
			CDAccount cdaTemp = new CDAccount(new CDOffering(term, interestRate), interestRate, accountNumber, balance,
					accountOpened);
			return cdaTemp;
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
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.YEAR, 5); // to get previous year add -1
		Date nextYear = cal.getTime();
		if (nextYear.after(currentDate)) {
			return false;
		} else {
			if (amount < 0) {
				return false;
			}
			double newBalance = this.getBalance() + amount;
			this.setBalance(newBalance);
			return true;
		}
	}

	@Override
	public String toString() {
		return generateStringForToString(getTerm());
	}

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
	public double futureValue() {
		double interestRate = offering.getInterestRate();
		int years = offering.getTerm();

		return balance * (Math.pow(1 + interestRate, years));
	}

}
