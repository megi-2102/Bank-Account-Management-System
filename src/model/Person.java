package model;

public class Person extends Customer{
	
	/**
	 * Represents an individual customer.
	 * All accounts are charged the same fee.
	 */
	
	public Person(String name, String address) {
		super(name, address);
	}
	
    /**
     * Charges all accounts owned by this person.
     * For a Person, the same fixed amount is withdrawn from each account.
     * This implements the abstract method from Customer.
     */

	@Override
	public void chargeAllAccounts(double amount)
	{
		for(Account a:getAccounts())
			a.withdraw(amount);
	}

}
