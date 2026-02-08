package service;

import java.util.List;

//Service interface for managing bank accounts.
//Defines operations for creating, removing, and retrieving accounts
public interface AccountService {
	
	//Creates a new account
	Account createAccount(Account account);
	
	//Removes an existing account
	void removeAccount(Account account);
	
	//Retrieves all accounts
	List<Account> getAccounts();
}
