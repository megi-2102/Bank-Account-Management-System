package service;

//Service interface responsible for calculating account fees based on the account balance
public interface FeeCalculatorService {

	//Calculates the applicable fee for a given account balance
	double calculateFee(double balance);
}
