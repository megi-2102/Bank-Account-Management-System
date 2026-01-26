package model;

public class Person extends Customer{
	
	/**
	 * Represents an individual customer.
	 * All accounts are charged the same fee.
	 */
	
	public Person(String name, String address) {
		super(name, address);
	}

	@Override
	public void chargeAllAccounts(double amount)
	{
		for(Account a:getAccounts())
			a.withdraw(amount);
	}

}
