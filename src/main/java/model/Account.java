package model;

/**
 * Abstract representation of a bank account.
 * Provides common balance operations and ID generation.
 */

public abstract class Account {
	
	private final long ACCOUNT_ID; // Unique identifier for each account, assigned automatically.
	private static long nextAccountId = 1_000;
	protected double balance;
	
	protected Account() {
		
		// Assign a unique ID when an account is created by incrementing by 5.
		this.ACCOUNT_ID = nextAccountId;
		nextAccountId += 5;
	}
	
	// Basic withdrawal operation.
	public double withdraw(double amount)
	{
		balance -= amount;	
		return amount;
	}
	
	// Basic deposit operation; ignores non-positive values
	public void deposit(double amount)
	{
		if(amount <= 0)
			return;
		balance += amount;
	}
	
	// Directly corrects the balance.
	public void correctBalance(double amount)
	{
		balance = amount;
	}
	
	public long getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
