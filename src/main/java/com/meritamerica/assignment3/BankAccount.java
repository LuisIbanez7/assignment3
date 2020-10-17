package com.meritamerica.assignment3;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BankAccount {
	private double interestRate;
	private double balance;
	private long accountNumber;
	private Date accountOpenedOn;

	private static long currentAccountNumber = 10000000000L;
	private static ArrayList<Long> usedAccountNumbers = new ArrayList<>();

	public BankAccount() {
		assignUniqueAccountNumToThisAccount();
	}

	public BankAccount(double balance) {
		assignUniqueAccountNumToThisAccount();

		this.balance = balance;
	}

	// Assignment 3 - Constructors
	public BankAccount(double balance, double interestRate) {
		assignUniqueAccountNumToThisAccount();

		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = getOpenedOn();
	}

	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		assignUniqueAccountNumToThisAccount(accountNumber);

		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = getOpenedOn();
	}

	private Date getOpenedOn() {
		Date newDate = new Date();
		return newDate;
	}

	private static BankAccount readFromString(String accountData) throws ParseException {
		// § Should throw a java.lang.NumberFormatException if String cannot be
		// correctly parsed
		BankAccount baTemp = new BankAccount();
		try {
			Double.parseDouble(accountData);
			return baTemp;
		} catch (NumberFormatException e) {
			throw e;
		}

	}

	@Override
	public String toString() {
		return generateStringForToString();
	}

	/*
	 * returns a string to be used in the toString method
	 */
	public String generateStringForToString() {
		StringBuilder str = new StringBuilder();

		str.append("Checking Account Balance: " + displayInUSD(getBalance()) + "\n");
		str.append("Checking Account Interest Rate : " + String.format("%.5f", getInterestRate()) + " \n");
		str.append("Checking Account Balance in 3 years: " + displayInUSD(futureValue(3)) + "\n");
		return str.toString();
		}
	public String generateStringForToString(int term) {
		StringBuilder str = new StringBuilder();

		str.append("Checking Account Balance: " + displayInUSD(getBalance()) + "\n");
		str.append("Checking Account Interest Rate : " + String.format("%.5f", getInterestRate()) + " \n");
		str.append("Checking Account Balance in 3 years: " + displayInUSD(futureValue(3)) + "\n");
		str.append("the term" + term);
		return str.toString();
		}
	
//////////////////////////////////////ASSIGNMENT 2 FROM HERE DOWN ////////////////////////////////////////
	
	/*
	 * returns the future account balance based on the current balance, the interest
	 * rate, and the specified number of years.
	 */
	public double futureValue(int years) {
		double balance = getBalance();
		double interestRate = getInterestRate();

		return balance * (Math.pow(1 + interestRate, years));
	}

	/*
	 * Will display an error message and return false if amount is either negative
	 * or greater than the account balance. A valid amount will result in amount
	 * subtracting from balance and true being returned from the method.
	 */
	public boolean withdraw(double amount) {
		if (amount < 0) {
			System.out.println("You may not withdraw a negative amount.");
			return false;
		}
		if (amount > getBalance()) {
			System.out.println("There is not enough money in the checking account " + "to make this widrawal");
			return false;
		}

		this.balance = getBalance() - amount;
		return true;
	}

	/*
	 * Will display an error message and return false if amount is negative. A valid
	 * amount will be added to balance.
	 */
	public boolean deposit(double amount) {
		if (amount < 0) {
			System.out.println("You may not deposit a negative amount.");
			return false;
		}

		this.balance = getBalance() + amount;
		return true;
	}

	/*
	 * Iterates the static variable, accountNum belonging to BankAccount class.
	 * assigns the resulting unique long to this BankAccount object upon object
	 * creation.
	 */
	private void assignUniqueAccountNumToThisAccount() {
		boolean flag = true;

		// if there are no used accountNumbers, we can dump
		// this account number straight in without checking if its
		// been used before
		if (usedAccountNumbers == null) {
			currentAccountNumber = currentAccountNumber + 1;
			this.accountNumber = currentAccountNumber;

			// if there is something inside of usedAccountNumbers, we
			// need to be more careful and check if the accountNumber
			// we are trying to use has been used before.
		} else {
			while (flag) {
				// increment currentAccountNumber until it is unique and doesn't
				// match anything inside usedAccountNumbers
				if (isAccountNumberUsed(currentAccountNumber)) {
					currentAccountNumber++;
				} else {
					usedAccountNumbers.add(currentAccountNumber);
					this.accountNumber = currentAccountNumber;
					flag = false;
				}
			}
		}
	}

	/*
	 * Iterates the static variable, accountNum belonging to BankAccount class.
	 * assigns the resulting unique long to this BankAccount object upon object
	 * creation.
	 */
	private void assignUniqueAccountNumToThisAccount(long anAccountNumber) {

		// if there are no used accountNumbers, we can dump
		// this anAccount number straight in without checking if its
		// been used before
		if (usedAccountNumbers == null) {
			this.accountNumber = anAccountNumber;

			// if there is something inside of usedAccountNumbers, we
			// need to be more careful and check if the accountNumber
			// we are trying to use has been used before.
		} else {
			if (isAccountNumberUsed(anAccountNumber)) {
				// throw an exception or warning message.
				System.out.println("The account number has already been used.");
			} else {
				usedAccountNumbers.add(anAccountNumber);
				this.accountNumber = anAccountNumber;
			}
		}
	}

	/*
	 * If the argument passed is found inside the static usedAccountNumbers field,
	 * the method will return true.
	 */
	public static boolean isAccountNumberUsed(long accNum) {
		boolean used = false;

		for (int i = 0; i < usedAccountNumbers.size(); i++) {
			if (accNum == usedAccountNumbers.get(i).longValue()) {
				used = true;
			}
		}

		return used;
	}

	//
	// Accessors and Mutators
	//
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public static long getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * Displays the state of the object as a string.
	 */


	/*
	 * returns the specified decimal formatted in United States Dollar
	 */
	public String displayInUSD(double decimal) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

		return formatter.format(decimal);
	}

}
