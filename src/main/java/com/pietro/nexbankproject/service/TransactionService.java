package com.pietro.nexbankproject.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.model.Transaction;
import com.pietro.nexbankproject.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		// TODO Auto-generated constructor stub
		this.transactionRepository = transactionRepository;
	}
	
	public List<Transaction> findSendings(Integer id) {
		List<Transaction> list = transactionRepository.findBySendingId(id);
		list.sort(Comparator.comparing(Transaction::getCod).reversed());
		return list;	
	}
	
	public List<Transaction> findReceivings(Integer id) {
		List<Transaction> list = transactionRepository.findByReceivingId(id);
		list.sort(Comparator.comparing(Transaction::getCod).reversed());
		return list;	
	}
	
	@Transactional
	public void add(Account sending, Account receiving, Double amount) {
		if (sending == null || receiving == null || amount == null) {
			return;
		}
		Transaction transaction = new Transaction();
		transaction.setCash(amount);
		transaction.setAge(LocalDate.now());
		transaction.setReceivingId(receiving.getId());
		transaction.setSendingId(sending.getId());
		
		transactionRepository.save(transaction);
	}
	
}
