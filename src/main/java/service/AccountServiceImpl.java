package service;

import java.util.List;
import repository.AccountReaderDAO;
import repository.AccountWriterDAO;

//Implementation of AccountService
public class AccountServiceImpl implements AccountService{

	private AccountReaderDAO accReaderDAO;
	private AccountWriterDAO accWriterDAO;

	//Constructor injection of DAO dependencies
	public AccountServiceImpl(AccountReaderDAO accReaderDAO, AccountWriterDAO accWriterDAO) {
		this.accReaderDAO = accReaderDAO;
		this.accWriterDAO = accWriterDAO;
	}

	//Retrieves accounts by delegating to AccountReaderDAO
	@Override
	public List<Account> getAccounts() {
        return accReaderDAO.readAccounts();
	}
	
	//Removes an existing account by delegating to AccountWriterDAO
    //Validates input before delegation
    @Override
    public void removeAccount(Account account) {
        if (account == null) 
            throw new IllegalArgumentException("Account cannot be null");
        accWriterDAO.deleteAccount(account);
    }

    //Creates a new account by delegating to AccountWriterDAO
    //Validates input before delegation
    @Override
    public Account createAccount(Account account) {
	    	if(account == null)
	    		throw new IllegalArgumentException("Account cannot be null");
        accWriterDAO.createAccount(account);
        return account;
    }
}
