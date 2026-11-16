package com.pietro.nexbankproject.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cod;
	
	@Column(name = "cash")
	private Double cash;
	
	@Column(name = "sendingId", nullable = false)
	private Integer sendingId;
	
	@Column(name = "receivingId", nullable = false)
	private Integer receivingId;
	
	@Column(name = "age")
	private LocalDate age; // datetime
	
	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Integer getSendingId() {
		return sendingId;
	}

	public void setSendingId(Integer sendingId) {
		this.sendingId = sendingId;
	}

	public Integer getReceivingId() {
		return receivingId;
	}

	public void setReceivingId(Integer receivingId) {
		this.receivingId = receivingId;
	}
	
	
}
