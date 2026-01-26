package model;

import java.util.ArrayList;

/**
 * Abstract base class representing a bank customer.
 * Each customer has a unique auto-generated ID and can hold multiple accounts.
 */

import java.util.List;

public abstract class Customer {
	
	private final long CUSTOMER_ID;
	private static long nextCustomerId = 2_000_000;
	private String name;
	private String address;
	private List<Account> accounts = new ArrayList<Account>();
	
	/**
	* Constructs a new Customer with the given name and address.
    * Automatically assigns a unique customer ID starting from 2000000 and incrementing by 7 for each new customer.
	*/
	
	public Customer(String name, String address) {
		this.CUSTOMER_ID = nextCustomerId;
		nextCustomerId +=7;
		this.name = name;
		this.address = address;
	}
	
	public void addAccount(Account account)
	{
		accounts.add(account);
	}
	
	public void removeAccount(Account account)
	{
		accounts.remove(account);
	}
	
    /**
     * Applies a charge to the customer's accounts.
     * Charging rules depend on the concrete customer type.
     */
	
	public abstract void chargeAllAccounts(double amount);
	
	public List<Account> getAccounts()
	{
		return accounts;
	}
	
	public long getCUSTOMER_ID()
	{
		return CUSTOMER_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
