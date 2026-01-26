package model;

//Checking account that tracks sequential check numbers.

public class CheckingAccount extends Account{
	
	private int nextCheckNumber = 1;

	public int getNextCheckNumber() {
		return nextCheckNumber++;
	}
}
