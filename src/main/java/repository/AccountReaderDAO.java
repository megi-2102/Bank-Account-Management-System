package repository;

import java.util.List;
import service.Account;

public interface AccountReaderDAO {

	List<Account> readAccounts();

}
