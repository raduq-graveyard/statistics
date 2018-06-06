package com.raduq.statistics.model;

import javax.validation.constraints.NotNull;

public class Event {

	@NotNull(message = "No amount was received in the transaction")
	private Double amount;
	@NotNull(message = "No timestamp was received in the transaction")
	private Long timestamp;

	public Event() {
	}

	public Event(Double amount, Long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

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
