package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import repository.AccountReaderDAO;
import repository.AccountWriterDAO;

//Unit tests for AccountServiceImpl
//DAO dependencies are mocked to verify service behavior in isolation
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

	AccountServiceImpl accountServiceImpl;
	
	@Mock
	AccountService mockAccountService;
	
	@Mock
    AccountReaderDAO mockReaderDAO;
	
	@Mock
    AccountWriterDAO mockWriterDAO;
	
	
	@BeforeEach
	void setUp() {
		accountServiceImpl = new AccountServiceImpl(mockReaderDAO, mockWriterDAO);
	}
	
	@Test
    void shouldSetAndGetAccountId() {
        Account account = new Account("initialId");
        assertEquals("initialId", account.getAccountId());
        account.setAccountId("updatedId");
        assertEquals("updatedId", account.getAccountId());
    }

	@Test
	void shouldReturnAccountsFromReaderDAO() {
		List<Account> accounts = new ArrayList<>(Arrays.asList(new Account("account1"), new Account("account2")));
        when(mockReaderDAO.readAccounts()).thenReturn(accounts);
        List<Account> result = accountServiceImpl.getAccounts();
        assertEquals(accounts, result);
        verify(mockReaderDAO).readAccounts();
        verifyNoInteractions(mockWriterDAO);
	}
	
	@Test
	void shouldCallDeleteAccountOnWriterDAO() {
		Account account = new Account("account1");
		accountServiceImpl.removeAccount(account);
        verify(mockWriterDAO).deleteAccount(account);
	}
	
	@Test
	void shouldCallDeleteAccountTwiceWhenRemovingTwoAccounts() {
	    Account acc1 = new Account("account1");
	    Account acc2 = new Account("account2");
	    accountServiceImpl.removeAccount(acc1);
	    accountServiceImpl.removeAccount(acc2);
	    verify(mockWriterDAO, times(2)).deleteAccount(any(Account.class));
	}
	
	@Test
	void shouldCallCreateAccountOnWriterDAO() {
		Account account = new Account("account1");
		accountServiceImpl.createAccount(account);
        verify(mockWriterDAO).createAccount(account);
	}
	
	@Test
	void shouldCallCreateAccounTwiceWhenCreatingTwoAccounts() {
		Account acc1 = new Account("account1");
		Account acc2 = new Account("account1");
		accountServiceImpl.createAccount(acc1);
		accountServiceImpl.createAccount(acc2);
        verify(mockWriterDAO, times(2)).createAccount(any(Account.class));
	}
	
	@Test
	void shouldReturnEmptyListWhenNoAccountsExist() {
	    when(mockReaderDAO.readAccounts()).thenReturn(List.of());
	    List<Account> result = accountServiceImpl.getAccounts();
	    assertNotNull(result);
	    assertTrue(result.isEmpty());
	}
	
	@Test
    void shouldThrowExceptionWhenCreatingNullAccount() {
		assertThrows(IllegalArgumentException.class,
	            () -> accountServiceImpl.createAccount(null));
	}
	
    @Test
    void shouldThrowExceptionWhenRemovingNullAccount() {
        assertThrows(IllegalArgumentException.class,
                () -> accountServiceImpl.removeAccount(null));
        verifyNoInteractions(mockWriterDAO);
    }
}
