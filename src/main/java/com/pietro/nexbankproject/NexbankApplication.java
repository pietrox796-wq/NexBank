package com.pietro.nexbankproject;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.repo.AccountRepository;

@SpringBootApplication
public class NexbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexbankApplication.class, args);
	}

	@Bean
	CommandLineRunner iniciarDatabase(AccountRepository repository) {
		return args -> {
			if (repository.count() == 0) {
				Account account = new Account();
				account.setUsername("Kluczkowski");
				account.setPassword("admin");
				account.encrypt();
				account.setAge(LocalDate.now());
				account.setCash((double) 5000);
				account.setCpf("12806529930");
				repository.save(account);
				
				Account account2 = new Account();
				account2.setUsername("Pietro");
				account2.setPassword("admin");
				account2.encrypt();
				account2.setAge(LocalDate.now());
				account2.setCash((double) 10000);
				account2.setCpf("11111111111");
				repository.save(account2);
			}
		};
		
	}
}
