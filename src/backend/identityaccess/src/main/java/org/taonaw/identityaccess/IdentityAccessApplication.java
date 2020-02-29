package org.taonaw.identityaccess;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.taonaw.identityaccess.domain.model.accounts.*;
import org.taonaw.identityaccess.infrastructure.repository.AccountRepository;

// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class IdentityAccessApplication {

	public static void main(String[] args) {

		var testAccount = Account.reconstruct(
				new AccountId("1"),
				new AccountName("太郎", "テスト"),
				new LoginId("test"),
				Password.of("12345678"));
		new AccountRepository().save(testAccount);

		SpringApplication.run(IdentityAccessApplication.class, args);
	}

}
