package model;

public class Company extends Customer{
	
	// Represents a company customer.
	
	public Company(String name, String address) {
		super(name, address);
	}

    /**
     * Charges all accounts owned by the company.
     * Checking accounts are charged the standard amount.
     * Savings accounts are charged double the standard amount.
     */
	
	@Override
	public void chargeAllAccounts(double amount)
	{
        for (Account account : getAccounts()) {
            if (account instanceof CheckingAccount) 
                account.withdraw(amount);
            else if (account instanceof SavingsAccount) 
                account.withdraw(amount * 2);
            }
        }
	}

