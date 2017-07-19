package io.pivotal.gemfire.client.dao;

import io.pivotal.gemfire.sdg.domain.Account;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * The AccountDaoImpl class is an implementation of the AccountDao interface used by client applications to load
 * Account information/data from an external data source on Cache misses, or stale, out-of-date Account data.
 *
 * @author nc
 * @see org.springframework.stereotype.Repository
 */
@Repository("accountDao")
public class InMemoryAccountDao implements AccountDao {

  private final AtomicInteger cacheMissCount = new AtomicInteger(0);

  private final AtomicLong ID_SEQUENCE = new AtomicLong(0l);

  private final ConcurrentMap<Integer, Account> inMemoryDataSource = new ConcurrentHashMap<Integer, Account>(51);

  protected final Logger log = LoggerFactory.getLogger(getClass().getName());

  @PostConstruct
  public void init() {
    log.info(String.format("%1$s initialized!", getClass().getSimpleName()));
  }

  @PreDestroy
  public void destroy() throws Exception {
    inMemoryDataSource.clear();
    cacheMissCount.set(0);
  }

  @Override
  public int getCacheMissCount() {
    return cacheMissCount.get();
  }

  @Override
  public Account load(final int accountId) {
    Account account = inMemoryDataSource.get(accountId);

    if (account == null) {
      throw new EmptyResultDataAccessException(String.format("Account with ID (%1$s) not found!", accountId), 1);
    }

    cacheMissCount.incrementAndGet();

    return new Account(account);
  }

  protected synchronized Long generateId() {
    long currentMaximumId = getCurrentMaximumId();
    long currentIdSequenceValue = ID_SEQUENCE.get();
    ID_SEQUENCE.addAndGet(Math.max(0, currentMaximumId - currentIdSequenceValue));
    return ID_SEQUENCE.incrementAndGet();
  }

  private synchronized long getCurrentMaximumId() {
    long currentMaximumId = 0l;

//    for (Long key : inMemoryDataSource.keySet()) {
//      currentMaximumId = Math.max(currentMaximumId, key);
//    }

    return currentMaximumId;
  }

  @Override
  public Account save(final Account account) {
    Assert.notNull(account, "The Account to save cannot be null!");

    synchronized (this) {
//      if (account.getId() == null) {
//        account.setId(generateId());
//      }

      inMemoryDataSource.put(account.getId(), account);
    }

    return account;
  }

}
