package io.pivotal.gemfire.client.dao;

import io.pivotal.gemfire.sdg.domain.Account;


/**
 * The AccountsDao interface is a Data Access Object (DAO) defining data access (CRUD-based) operations on Accounts
 * stored in external data source(s).
 *
 * @author nc
 */
public interface AccountDao {

  int getCacheMissCount();

  Account load(int accountId);

  Account save(Account account);

}
