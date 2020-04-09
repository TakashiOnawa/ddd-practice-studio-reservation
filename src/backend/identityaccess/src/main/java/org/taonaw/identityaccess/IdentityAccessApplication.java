package org.taonaw.identityaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.taonaw.identityaccess.domain.model.account.*;
import org.taonaw.identityaccess.domain.model.shared.FullName;
import org.taonaw.identityaccess.domain.model.shared.PlainTextPassword;
import org.taonaw.identityaccess.infrastructure.repository.AccountRepository;
import org.taonaw.identityaccess.infrastructure.service.BCryptPasswordEncoder;

// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class IdentityAccessApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(IdentityAccessApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var encoder = new BCryptPasswordEncoder(passwordEncoder);
		var testAccount = Account.reconstruct(
				new AccountId("1"),
				new FullName("太郎", "テスト"),
				new LoginId("test"),
				new PlainTextPassword("12345678").encode(encoder));
		new AccountRepository().add(testAccount);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
}
