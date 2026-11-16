package com.pietro.nexbankproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.nexbankproject.model.BankOperation;


public interface BankOperationRepository extends JpaRepository<BankOperation, Integer> {
	
}