package com.pietro.nexbankproject.model;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//Spring Communication
	@Column(name = "cash", nullable = false)
	private Double cash; // Big Decimal
	//Spring Communication
	@Column(name = "username", nullable = false, length = 60)
	private String username; // varchar(60)
	//Spring Communication
	@Column(name = "level")
	private Integer level; // int
	//Spring Communication
	@Column(name = "age")
	private LocalDate age; // datetime
	//Spring Communication
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf; // varchar(11)
	//Spring Communication
	@Column(name = "password", nullable = false, length = 60)
	private String password; // varchar(60)
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Constructor
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}
	
	public void encrypt() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(this.password);
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void addCash(Double amount) {
		this.cash += amount;
	}
	
	public boolean send(Account receiving, Double amount) {
		if (receiving != null && amount != null && amount > 0 && amount <= this.cash) {
			receiving.addCash(amount);
			addCash(-amount);
			return true;
		}
		return false;
	}
}
