package com.raduq.statistics.model;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.raduq.statistics.exception.TransactionTimeoutException;

public class Transaction {

	public static final int TIMEOUT = 60;

	private Event event;

	public Transaction(Event event) {
		this.event = validate( event );
	}

	public Long getTimestamp() {
		return this.event.getTimestamp();
	}

	public Double getAmount() {
		return this.event.getAmount();
	}

	private Event validate(Event transaction) {
		LocalDateTime timestamp = getDateTime( transaction.getTimestamp() );
		LocalDateTime timedout = LocalDateTime.now( ZoneOffset.UTC ).minus( TIMEOUT, SECONDS );

		if (timestamp.isBefore( timedout ) || timestamp.isEqual( timedout ))
			throw new TransactionTimeoutException( transaction.getTimestamp() );

		return transaction;
	}

	private LocalDateTime getDateTime(Long timestamp) {
		return Instant.ofEpochMilli( timestamp ).atOffset( ZoneOffset.UTC ).toLocalDateTime();
	}

	private LocalDateTime getTimeout() {
		return LocalDateTime.now( ZoneOffset.UTC ).plus( TIMEOUT, SECONDS );
	}
}
