package com.pietro.nexbankproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.nexbankproject.model.Account;



public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByUsername(String username);
	
	public Account findByPassword(String password);
	
	public Account findByid(Integer id);
	
	public Account findByCpf(String cpf);
}
