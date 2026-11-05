package com.pietro.nexbankproject.model;

import java.time.LocalDate;

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
	private Double cash;
	//Spring Communication
	@Column(name = "username", nullable = false, length = 60)
	private String username;
	//Spring Communication
	@Column(name = "level")
	private Integer level;
	//Spring Communication
	@Column(name = "age")
	private LocalDate age;
	
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

	public Double getMoney() {
		return cash;
	}

	public void setMoney(Double cash) {
		this.cash = cash;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String username) {
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
}
