package model;

// Savings account that prevents overdrafts and supports interest.

public class SavingsAccount extends Account{
	
	private double interestRate;
	
	public SavingsAccount(double interestRate) {
		super();
		this.interestRate = interestRate;
	}
	
	@Override
	public double withdraw(double amount)
	{
		if (amount > balance) 
			return 0;
		balance -= amount;
		return amount;
	}

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
