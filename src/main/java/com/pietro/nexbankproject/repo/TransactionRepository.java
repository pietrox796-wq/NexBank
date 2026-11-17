package com.pietro.nexbankproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.nexbankproject.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findBySendingId(Integer sendingId);

    public List<Transaction> findByReceivingId(Integer receivingId);

}