package com.pietro.nexbankproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BankOperation")
public class BankOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cod; // int
	
	@Column(name = "sendingId", nullable = false)
	private Integer sendingId; // int
	
	@Column(name = "receivingId", nullable = false)
	private Integer receivingId; // int
	
	@Column(name = "cash", nullable = false)
	private Double cash; // Decimal
	
	public BankOperation() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public Integer getSending() {
		return sendingId;
	}

	public void setSending(Integer sendingId) {
		this.sendingId = sendingId;
	}

	public Integer getReceivingId() {
		return receivingId;
	}

	public void setReceiving(Integer receivingId) {
		this.receivingId = receivingId;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}
}

