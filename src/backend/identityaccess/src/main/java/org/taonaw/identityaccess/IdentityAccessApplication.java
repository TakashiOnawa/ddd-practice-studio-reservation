package org.taonaw.identityaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class IdentityAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityAccessApplication.class, args);
	}

}
