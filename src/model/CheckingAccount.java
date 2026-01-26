package model;

//Checking account that tracks sequential check numbers.

public class CheckingAccount extends Account{
	
	private int nextCheckNumber = 1; // Tracks next check number.

	public int getNextCheckNumber() {
		return nextCheckNumber++; // Returns and increments check number.
	}
}
