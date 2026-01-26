package model;

// Savings account that prevents overdrafts and supports interest.

public class SavingsAccount extends Account{
	
	private double interestRate; // Interest rate in percent.
	
	public SavingsAccount(double interestRate) {
		super();
		this.interestRate = interestRate;
	}
	
	@Override
	public double withdraw(double amount)
	{
		// Prevents overdraft: cannot withdraw more than current balance.
		if (amount > balance) 
			return 0;
		balance -= amount;
		return amount;
	}
	
	// Adds interest to the current balance based on interestRate.
	public void addInterest()
	{
		balance += balance * interestRate / 100;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double rate) {
		this.interestRate = rate;
	}
}
