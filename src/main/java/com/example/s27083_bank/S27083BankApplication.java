package com.example.s27083_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class S27083BankApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(S27083BankApplication.class, args);
	}

}
