package repository;

import service.Account;

public interface AccountWriterDAO {
	
	Account createAccount(Account account);
	void deleteAccount(Account account);
	
}
