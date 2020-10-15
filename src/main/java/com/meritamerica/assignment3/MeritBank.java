package com.meritamerica.assignment3;

import java.text.ParseException;

public class MeritBank {

	private static AccountHolder[] accountHolders;
	private static CDOffering[] cdOfferings;

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

		if(currentOfferings != null) {
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

		if(bestCDOffering != null) {
			double highestYield = futureValue(depositAmount, bestCDOffering.getInterestRate(), bestCDOffering.getTerm());

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
				if(accountHolders[i] != null) {
					totalBalancesOfAccountHolders = 
							totalBalancesOfAccountHolders + accountHolders[i].getCombinedBalance();
				}
			}
			return totalBalancesOfAccountHolders;
		}
	}

	public static double futureValue(double presentValue, double interestRate, int term) {

		return presentValue * (Math.pow(1 + interestRate, term));
	}
	public static boolean readFromFile(String str) throws ParseException {
//		  try {  
//			    Double.parseDouble(str);  
//			    return true;
//			  } catch(NumberFormatException e){  
//			    return false;  
//			  }  
		return true;
	}
	public static boolean writeToFile(String fileName) {
		return true;
	}


}
