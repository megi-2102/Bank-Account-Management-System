package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

//Coordinates creation and removal of customers and accounts.

public class AccountController {
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Account> accounts = new ArrayList<Account>();
	
	public Customer createCustomer(String name, String address, String type)
	{
		Customer customer;
		
		if(type.equalsIgnoreCase("person"))
			customer = new Person(name, address);
		else if(type.equalsIgnoreCase("company"))
			customer = new Company(name, address); 
		else
			throw new IllegalArgumentException("Invalid customer type");
		customers.add(customer);
		return customer;
	}

	public Account createAccount(Customer customer, String type)
	{
        Account account;
        if (type.equalsIgnoreCase("checking")) {
            account = new CheckingAccount();
        } else if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(0);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        accounts.add(account);
        customer.addAccount(account);
        return account;
	}
	
	public void removeCustomer(Customer customer)
	{
        accounts.removeAll(customer.getAccounts());
        customers.remove(customer);
	}
	
	public void removeAccount(Account account)
	{
		accounts.remove(account);
	    for (Customer c : customers) {
	        c.removeAccount(account);
	    }
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
}
