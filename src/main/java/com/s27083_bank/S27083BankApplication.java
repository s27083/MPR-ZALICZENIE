package com.s27083_bank;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.s27083_bank.model.Currency;
import com.s27083_bank.model.User;
import com.s27083_bank.service.UserService;

@SpringBootApplication
public class S27083BankApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(S27083BankApplication.class, args);
		UserService userService = context.getBean("userService", UserService.class);
		userService.registerUser(new User(null, "Jan", "Kowalski", "1234", 1000, Currency.PLN , "11111111111"));
	}

}
