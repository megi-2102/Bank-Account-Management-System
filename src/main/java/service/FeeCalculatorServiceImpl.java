package service;

//Implementation of the FeeCalculationService developed using Test-Driven Development (TDD)
public class FeeCalculatorServiceImpl implements FeeCalculatorService {

	//Calculates the fee based on balance ranges
    @Override
    public double calculateFee(double balance) {
    	
    	//Exception thrown to prevent invalid input
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        } else if (balance <= 100.0) {
            return 20.0;
        } else if (balance <= 500.0) {
            return 15.0;
        } else if (balance <= 1000.0) {
            return 10.0;
        } else if (balance <= 2000.0) {
            return 5.0;
        } else {
            return 0.0;
        }
    }
}
