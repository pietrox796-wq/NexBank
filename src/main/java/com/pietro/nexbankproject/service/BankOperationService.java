package com.pietro.nexbankproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.model.BankOperation;
import com.pietro.nexbankproject.repo.AccountRepository;
import com.pietro.nexbankproject.repo.BankOperationRepository;

import jakarta.transaction.Transactional;

@Service
public class BankOperationService {

	private final AccountRepository accountRepository;
	private final BankOperationRepository bankOperationRepository;

	public BankOperationService(BankOperationRepository bankOperationRepository, AccountRepository accountRepository) {
		this.bankOperationRepository = bankOperationRepository;
		this.accountRepository = accountRepository;
	}

	@Transactional
	public boolean save(BankOperation bankOperation) {
		try {
			bankOperationRepository.save(bankOperation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Page<BankOperation> listAll(Pageable pageable) {
		return bankOperationRepository.findAll(pageable);
	}
	
	public Account authenticate(Integer id) {
		try {
			Account current = accountRepository.findByid(id);
			return current;	
		} catch(Exception e) {
			return null;
		}
	}
}