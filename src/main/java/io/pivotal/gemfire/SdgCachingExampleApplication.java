package io.pivotal.gemfire;

import io.pivotal.gemfire.client.dao.AccountDao;
import io.pivotal.gemfire.client.service.AccountService;
import io.pivotal.gemfire.sdg.domain.Account;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
@ImportResource("spring/config/sdg-client-cache.xml")
public class SdgCachingExampleApplication {

	@Resource(name = "accountDao")
	private AccountDao accountDao;

	@Resource(name = "accountService")
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(SdgCachingExampleApplication.class, args);
	}

	/*private Account newAccount(final int id, final String firstName, final String lastName) {
		Account account = new Account(id, firstName, lastName);
		return account;
	}

	public void setUp() {
		accountDao.save(newAccount(1, "Amey", "Banarse"));
		accountDao.save(newAccount(2, "Matt", "Ross"));
		accountDao.save(newAccount(3, "Nikhil", "Chandrappa"));
	}

	@Bean
	CommandLineRunner keepOpen() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				// Setup sample Data
				setUp();

				while (true) {
					int accountKey = 1;
					Account account = accountService.getAccount(accountKey);
					System.out.println("Account Info for Key: " + accountKey);
					System.out.println(account.toString());
					System.out.println("\nCache Miss Count: " + accountDao.getCacheMissCount());

					System.out.println("\nSecond Invocation for key: " + accountKey);
					account = accountService.getAccount(accountKey);
					System.out.println("Account Info for: " + accountKey);
					System.out.println(account.toString());
					System.out.println("\nCache Miss Count: " + accountDao.getCacheMissCount());
				}

			}

		};
	}*/
}
