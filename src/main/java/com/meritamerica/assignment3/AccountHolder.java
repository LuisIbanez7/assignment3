package com.meritamerica.assignment3;

/*
 * This is the definition of the AccountHolder class.
 * It is meant to simulate an account holder of a banking application.
 */
public class AccountHolder implements Comparable<AccountHolder> {

	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;

	private CheckingAccount[] checkingAccounts;
	private SavingsAccount[] savingsAccounts;
	private CDAccount[] cdAccounts;
	//private Comparable <AccountHolder>;

	/*
	 * no arg constructor
	 */
	

	public AccountHolder() {
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.ssn = "";

//		this.checkingAccounts = new CheckingAccount[0];
//		this.savingsAccounts = new SavingsAccount[0];
//		this.cdAccounts = new CDAccount[0];
	}

	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
	}
	public void compateTo(AccountHolder otherAccountHolder) {
		return this.getCombinedBalance(getBalance)
	}
	public SavingsAccount addSavingsAccount(double openingBalance) {
		SavingsAccount newAccount = new SavingsAccount(openingBalance);
		SavingsAccount[] currentArray = getSavingsAccounts();

		if (currentArray == null) {
			SavingsAccount[] newSavingsAccountArray = new SavingsAccount[1];
			newSavingsAccountArray[0] = newAccount;
			this.savingsAccounts = newSavingsAccountArray;
		} 
		else if ((getSavingsBalance()) < 250000) {
			SavingsAccount[] newSavingsAccountArray = new SavingsAccount[currentArray.length + 1];

			for (int i = 0; i < currentArray.length; i++) {
				newSavingsAccountArray[i] = currentArray[i];
			}
			newSavingsAccountArray[newSavingsAccountArray.length - 1] = newAccount;
			this.savingsAccounts = newSavingsAccountArray;
		} 
		else {
			System.out.println("Your combined savings and checking account balances" + " must be less than 250,000");
		}

		return newAccount;
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsObj) {
		SavingsAccount[] currentArray = getSavingsAccounts();
		double sum = getSavingsBalance() + getCheckingBalance() + savingsObj.getBalance();
		if (currentArray == null) {
			if (sum <= 250000) {
				SavingsAccount[] newSavingsAccountArray = new SavingsAccount[1];
				newSavingsAccountArray[0] = savingsObj;
				this.savingsAccounts = newSavingsAccountArray;
			} else {
				System.out
						.println("Your combined savings and checking account balances" + " must be less than 250,000");
			}
		} else if ((sum) <= 250000) {
			SavingsAccount[] newSavingsAccountArray = new SavingsAccount[currentArray.length + 1];

			for (int i = 0; i < currentArray.length; i++) {
				newSavingsAccountArray[i] = currentArray[i];
			}
			newSavingsAccountArray[newSavingsAccountArray.length - 1] = savingsObj;
			this.savingsAccounts = newSavingsAccountArray;
		} else {
			System.out.println("Your combined savings and checking account balances" + " must be less than 250,000");
		}

		return savingsObj;
	}

	/*
	 * When a new CheckingAccount object is added to the checkingAccounts array a
	 * new array will be created. The old contents of the checkingAccounts array
	 * must be copied in to a new array along with the new CheckingAccount object.
	 * The new array is then assigned back to the checkingAccounts array.
	 */
	public CheckingAccount addCheckingAccount(double openingBalance) {

		double sum = getCheckingBalance() + getSavingsBalance();
		CheckingAccount newAccount = new CheckingAccount(openingBalance);
		CheckingAccount[] currentArray = getCheckingAccounts();

		if (sum > 250000) {
			if (currentArray == null) {
				CheckingAccount[] newCheckingAccountArray = new CheckingAccount[1];
				newCheckingAccountArray[0] = newAccount;
				this.checkingAccounts = newCheckingAccountArray;
			}
		} else if (sum < 250000) {

			sum = getCheckingBalance() + getSavingsBalance();

			if (currentArray == null) {
				CheckingAccount[] newCheckingAccountArray = new CheckingAccount[1];
				newCheckingAccountArray[0] = newAccount;
				this.checkingAccounts = newCheckingAccountArray;
			} else if (sum < 250000) {

				CheckingAccount[] newCheckingAccountArray = new CheckingAccount[currentArray.length + 1];

				for (int i = 0; i < currentArray.length; i++) {
					newCheckingAccountArray[i] = currentArray[i];
				}
				newCheckingAccountArray[newCheckingAccountArray.length - 1] = newAccount;
				this.checkingAccounts = newCheckingAccountArray;
			} else {
				System.out
						.println("Your combined savings and checking account balances" + " must be less than 250,000");
			}
			return newAccount;
		} else {
			System.out.println("Your combined savings and checking account balances" + " must be less than 250,000");
			return newAccount;
		}
		return newAccount;
	}

	public CheckingAccount addCheckingAccount(CheckingAccount checkingObj) {
		CheckingAccount[] currentArray = getCheckingAccounts();
		double sum = getCheckingBalance() + getSavingsBalance() + checkingObj.getBalance();

		if (currentArray == null) {
			if (sum <= 250000) {
				CheckingAccount[] newCheckingAccountArray = new CheckingAccount[1];
				newCheckingAccountArray[0] = checkingObj;
				this.checkingAccounts = newCheckingAccountArray;
			} else {
				System.out.println(
						"The balances from your savings and checking " + "accounts must total less than 250,000.");
			}
		} else if (sum <= 250000) {
			CheckingAccount[] newCheckingAccountArray = new CheckingAccount[currentArray.length + 1];

			for (int i = 0; i < currentArray.length; i++) {
				newCheckingAccountArray[i] = currentArray[i];
			}
			newCheckingAccountArray[newCheckingAccountArray.length - 1] = checkingObj;
			this.checkingAccounts = newCheckingAccountArray;
		} else {
			System.out.println("Your combined savings and checking account balances" + " must be less than 250,000");
		}

		return checkingObj;
	}

	public double getCheckingBalance() {
		CheckingAccount[] currentCheckingAccount = getCheckingAccounts();
		double checkingBalance = 0;
		if (currentCheckingAccount != null) {
			for (int i = 0; i < currentCheckingAccount.length; i++) {
				checkingBalance = checkingBalance + currentCheckingAccount[i].getBalance();
			}
		}

		return checkingBalance;
	}

	public double getSavingsBalance() {
		SavingsAccount[] currentSavingsAccount = getSavingsAccounts();
		double savingsBalance = 0;
		if (currentSavingsAccount != null) {
			for (int i = 0; i < currentSavingsAccount.length; i++) {
				savingsBalance = savingsBalance + currentSavingsAccount[i].getBalance();
			}
		}

		return savingsBalance;
	}

	//
	// Accessors and mutators
	//
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccount(CheckingAccount[] checkingAccount) {
		this.checkingAccounts = checkingAccount;
	}

	public SavingsAccount[] getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccount(SavingsAccount[] savingsAccount) {
		this.savingsAccounts = savingsAccount;
	}

	public CDAccount[] getCDAccounts() {

		return cdAccounts;
	}

	public CDAccount addCDAccount(CDAccount cdAccount) {
		CDAccount[] currentArray = getCDAccounts();
		if (currentArray == null) {
			CDAccount[] newArray = new CDAccount[1];
			newArray[0] = cdAccount;
			this.cdAccounts = newArray;
		} else {
			CDAccount[] newArray = new CDAccount[currentArray.length + 1];
			for (int i = 0; i < currentArray.length; i++) {
				newArray[i] = currentArray[i];
			}
			this.cdAccounts = newArray;
		}
		return cdAccount;
	}

	public CDAccount addCDAccount(CDOffering cdOfferObj, double balance) {
		// CDAccount cd = new CDAccount();
		if (cdOfferObj != null) {
			CDAccount cd = new CDAccount(cdOfferObj, balance);
			CDAccount[] currentArray = getCDAccounts();
			if (currentArray == null) {
				CDAccount[] newArray = new CDAccount[1];
				newArray[0] = cd;
				this.cdAccounts = newArray;
			} else {

				CDAccount[] newArray = new CDAccount[currentArray.length + 1];
				for (int i = 0; i < currentArray.length; i++) {
					newArray[i] = currentArray[i];
				}
				this.cdAccounts = newArray;

			}
			return cd;

		}

		return null;
	}

	public double getCDBalance() {
		CDAccount[] currentArray = getCDAccounts();
		// System.out.println(currentArray.length);
		double sum = 0;
		if (currentArray != null) {
			for (int i = 0; i < currentArray.length; i++) {
				sum = sum + currentArray[i].getBalance();
			}
		}
		return sum;
	}

	public double getCombinedBalance() {

		return getCheckingBalance() + getSavingsBalance() + getCDBalance();
	}

	public int getNumberOfCheckingAccounts() {
		int numberOfCheckingAccounts = 0;

		if (checkingAccounts == null) {
			return numberOfCheckingAccounts;
		} else {
			return checkingAccounts.length;
		}
	}

	public int getNumberOfSavingsAccounts() {
		int numberOfSavingsAccounts = 0;
		if (savingsAccounts == null) {
			return numberOfSavingsAccounts;
		} else {
			return savingsAccounts.length;
		}
	}

	public int getNumberOfCDAccounts() {
		int numberOfCDAccounts = 0;
		if (cdAccounts == null) {
			return numberOfCDAccounts;
		} else {
			return cdAccounts.length;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * returns the state of the object as a string.
	 */
	@Override
	public String toString() {
		return generateStringForToString();
	}

	/*
	 * returns a string to be used in the toString method.
	 */
	private String generateStringForToString() {
		StringBuilder str = new StringBuilder();

		// append the name
		str.append("Name: " + getFirstName() + " " + getMiddleName() + " " + getLastName() + "\n");

		// append social security number
		str.append("SSN: " + getSSN() + "\n");

		// append checking account balance
		// append checking account interest rate
		// append checking account balance in 3 years
		str.append(getCheckingAccounts().toString());

		// append savings account balance
		// append savings account interest rate
		// append the savings account balance in 3 years
		str.append(getSavingsAccounts().toString());

		// return the StringBuilder object as a string.
		return str.toString();
	}
}