package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;

//Coordinates creation and removal of customers and accounts.

public class AccountController {
	private List<Customer> customers = new ArrayList<>();
	private List<Account> accounts = new ArrayList<>();
	
	public Customer createCustomer(String name, String address, String type)
	{
		Customer customer;
		
        // Dynamically decides which subclass of Customer to instantiate based on the 'type' argument.
        // This allows handling both individuals (Person) and organizations (Company) using polymorphism.
		
		if(type.equalsIgnoreCase("person"))
			customer = new Person(name, address);
		else if(type.equalsIgnoreCase("company"))
			customer = new Company(name, address); 
		else
			throw new IllegalArgumentException("Invalid customer type");
		customers.add(customer); // Adds the new customer to the managed list
		return customer;
	}

	public Account createAccount(Customer customer, String type)
	{
        Account account;
        
        // Chooses the account type. SavingsAccount is initialized with a default balance of 0.
        if (type.equalsIgnoreCase("checking")) {
            account = new CheckingAccount();
        } else if (type.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(0);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        accounts.add(account);
        customer.addAccount(account); // Links the account to the corresponding customer.
        return account;
	}
	
	// Ensures all accounts of this customer are  removed together with the customer. 
	public void removeCustomer(Customer customer)
	{
        accounts.removeAll(customer.getAccounts());
        customers.remove(customer);
	}
	
	// Removes an account and iterates through all customers to remove this account reference.
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
