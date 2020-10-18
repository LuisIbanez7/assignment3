package com.meritamerica.assignment3;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeritBank {

	private static AccountHolder[] accountHolders;
	private static CDOffering[] cdOfferings;
	private static Long nextAccountNumber;

	public static void addAccountHolder(AccountHolder accountHolder) {
		// if the accountHolders array has no accounts in it, we initialize it
		// with a new array containing one index and add accountHolder to the index.
		if (accountHolders == null) {
			AccountHolder[] newArray = new AccountHolder[1];
			newArray[0] = accountHolder;
			accountHolders = newArray;

		} else {
			// create a new array with the contents of old array and new accountHolder
			AccountHolder[] oldArray = getAccountHolders();
			AccountHolder[] newArray = new AccountHolder[oldArray.length + 1];

			for (int i = 0; i < oldArray.length; i++) {
				newArray[i] = oldArray[i];
			}

			accountHolders = newArray;
		}
	}

	public static boolean readFromFile(String fileName) {

		try {
			String strCurrentLine;
			ArrayList myList = new ArrayList();
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((strCurrentLine = br.readLine()) != null) {

				myList.add(strCurrentLine);
				// System.out.println(strCurrentLine);
			}

			for (int i = 0; i < myList.size(); i++) {
				switch (i) {
				case 0:
					Long num = Long.parseLong(myList.get(i).toString());
					setNextAccountNumber(num);
					break;
				case 1:
					//System.out.println("Monday");
					break;
				case 2:
					String[] firstOffering = myList.get(i).toString().split(",");
					String[] secondOffering = myList.get(i+1).toString().split(",");
					String[] thirdOffering = myList.get(i+2).toString().split(",");
					CDOffering first = new CDOffering(Integer.parseInt(firstOffering[0]), Double.parseDouble(firstOffering[1]));
					CDOffering second = new CDOffering(Integer.parseInt(secondOffering[0]), Double.parseDouble(secondOffering[1]));
					CDOffering third = new CDOffering(Integer.parseInt(thirdOffering[0]), Double.parseDouble(thirdOffering[1]));
	
					CDOffering[] offerings = new CDOffering[3];
					offerings[0] = first;
					offerings[1] = second;
					offerings[2] = third;
					setCDOfferings(offerings);

					break;
				case 6:
					String[] ah1Data = myList.get(i).toString().split(",");
					AccountHolder ah1 = new AccountHolder(ah1Data[2], ah1Data[1], ah1Data[0], ah1Data[3]);
					addAccountHolder(ah1);
					break;
				case 8:
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					String[] ah1Checking1Data = myList.get(i).toString().split(",");
					Date accountOpened = dateFormatter.parse(ah1Checking1Data[3]);

					AccountHolder[] ah1Array = getAccountHolders();
					CheckingAccount ah1Checking1 = new CheckingAccount(Long.parseLong(ah1Checking1Data[0]),Double.parseDouble(ah1Checking1Data[2]), Integer.parseInt(ah1Checking1Data[1]), accountOpened);
					ah1Array[0].addCheckingAccount(ah1Checking1);
					break;
				case 10:
					SimpleDateFormat dateFormatter1 = new SimpleDateFormat("dd/MM/yyyy");
					String[] ah1Savings1Data1 = myList.get(i).toString().split(",");
					Date accountOpened1 = dateFormatter1.parse(ah1Savings1Data1[3]);

					AccountHolder[] ah1Array1 = getAccountHolders();
					SavingsAccount ah1Savings1 = new SavingsAccount(Long.parseLong(ah1Savings1Data1[0]), Integer.parseInt(ah1Savings1Data1[1]), Double.parseDouble(ah1Savings1Data1[2]), accountOpened1);
					ah1Array1[0].addSavingsAccount(ah1Savings1);
					break;
				case 11:
					SimpleDateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
					String[] ah1Savings2Data = myList.get(i).toString().split(",");
					Date accountOpened2 = dateFormatter2.parse(ah1Savings2Data[3]);

					AccountHolder[] ah1Array2 = getAccountHolders();
					SavingsAccount ah1Savings2 = new SavingsAccount(Long.parseLong(ah1Savings2Data[0]), Integer.parseInt(ah1Savings2Data[1]), Double.parseDouble(ah1Savings2Data[2]), accountOpened2);
					ah1Array2[0].addSavingsAccount(ah1Savings2);
					break;
				case 12:
					
					break;
				default:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public static boolean writeToFile(String fileName) throws IOException {

		// StringBuilder str = new StringBuilder();
//
//		str.append("Checking Account Balance: " + displayInUSD(getBalance()) + "\n");
//		str.append("Checking Account Interest Rate : " + String.format("%.5f", getInterestRate()) + " \n");
//		str.append("Checking Account Balance in 3 years: " + displayInUSD(futureValue(3)) + "\n");
//		str.append("the term" + term);
//		return str.toString();
//		
		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\Luis\\Google Drive\\MeritAmerica\\Week 4\\assignment3\\src\\main\\java\\com\\meritamerica\\assignment3\\BankData.text");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
		printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
		printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
		printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);

		printWriter.close();

		return false;

	}

	public static AccountHolder[] sortAccountHolders() {
		return accountHolders;

	}

	public static void setNextAccountNumber(long nextAccountNumber) {
		MeritBank.nextAccountNumber = nextAccountNumber;
	}

	public static AccountHolder[] getAccountHolders() {
		return accountHolders;
	}

	public static CDOffering[] getCDOfferings() {

		return cdOfferings;
	}

	public static CDOffering getBestCDOffering(double depositAmount) {
		CDOffering[] currentOfferings = getCDOfferings();
		CDOffering bestCDOffering = null;
		double highestYield = 0;

		if (currentOfferings != null) {
			for (int i = 0; i < currentOfferings.length; i++) {
				double yield = futureValue(depositAmount, currentOfferings[i].getInterestRate(),
						currentOfferings[i].getTerm());

				if (yield > highestYield) {
					highestYield = yield;
					bestCDOffering = currentOfferings[i];
				}
			}
		}

		return bestCDOffering;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		CDOffering[] currentOfferings = getCDOfferings();

		CDOffering bestCDOffering = getBestCDOffering(depositAmount);
		CDOffering secondBestCDOffering = null;

		if (bestCDOffering != null) {
			double highestYield = futureValue(depositAmount, bestCDOffering.getInterestRate(),
					bestCDOffering.getTerm());

			double secondHighestYield = 0;

			for (int i = 0; i < currentOfferings.length; i++) {
				double yield = futureValue(depositAmount, currentOfferings[i].getInterestRate(),
						currentOfferings[i].getTerm());

				if (yield != highestYield && yield > secondHighestYield) {
					secondHighestYield = yield;
					secondBestCDOffering = currentOfferings[i];
				}
			}
		}

		return secondBestCDOffering;
	}

	public static void clearCDOfferings() {
		cdOfferings = null;
	}

	public static void setCDOfferings(CDOffering[] newCDOfferings) {
		cdOfferings = newCDOfferings;
	}

	public static long getNextAccountNumber() {
		long nextAccountNumber = BankAccount.getCurrentAccountNumber() + 1;
		return nextAccountNumber;
	}

	public static double totalBalances() {
		double totalBalancesOfAccountHolders = 0;
		if (accountHolders == null) {
			return totalBalancesOfAccountHolders;
		} else {
			for (int i = 0; i < accountHolders.length; i++) {
				if (accountHolders[i] != null) {
					totalBalancesOfAccountHolders = totalBalancesOfAccountHolders
							+ accountHolders[i].getCombinedBalance();
				}
			}
			return totalBalancesOfAccountHolders;
		}
	}

	public static double futureValue(double presentValue, double interestRate, int term) {

		return presentValue * (Math.pow(1 + interestRate, term));
	}

}
