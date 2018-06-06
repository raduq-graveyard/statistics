package com.raduq.statistics.model;

public class Transaction {

	private Double amount;
	private Long timestamp;

	public Transaction(){}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
