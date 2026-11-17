package com.pietro.nexbankproject.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.repo.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final BCryptPasswordEncoder encoder;
	
	public AccountService(AccountRepository accountRepository) {
		// TODO Auto-generated constructor stub
		this.accountRepository = accountRepository;
		this.encoder = new BCryptPasswordEncoder();
	}

	@Transactional
	public boolean save(Account account) {
	    account.encrypt();
	    accountRepository.save(account);
	    return true;
	}
	
	@Transactional
	public void update(Account account) {
	    accountRepository.save(account);
	}
	
	public Account authenticate(String cpf, String password) {
		Account account = accountRepository.findByCpf(cpf);
		
		if (account != null && encoder.matches(password, account.getPassword())) {
			return account;
		}		
		return null;
	}
	
	public Account authenticateByCpf(String cpf) {
		Account account = accountRepository.findByCpf(cpf);
		
		if (account != null) {
			return account;
		}		
		return null;
	}
	
}
