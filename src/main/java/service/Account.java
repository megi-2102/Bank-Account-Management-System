package service;

//Represents a bank account in the system
public class Account {
	
	//Unique identifier for the account
	private String accountId;

	//Constructs an Account with the specified account ID
	public Account(String accountId) {
		this.accountId = accountId;
	}

	//Returns the account identifier
	public String getAccountId() {
		return accountId;
	}

	//Updates the account identifier
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
