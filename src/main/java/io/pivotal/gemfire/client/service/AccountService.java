package io.pivotal.gemfire.client.service;

import io.pivotal.gemfire.sdg.domain.Account;

/**
 * The AccountService class is a Service component for processing Accounts.
 *
 * @author nc
 */
public interface AccountService {

  Account getAccount(int accountId);

}
