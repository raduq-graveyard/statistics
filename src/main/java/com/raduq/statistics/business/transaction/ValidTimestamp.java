package com.raduq.statistics.business.transaction;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

import com.raduq.statistics.business.transaction.exception.InvalidTimestampException;
import com.raduq.statistics.business.transaction.exception.TransactionTimeoutException;

public class ValidTimestamp {

	private static final String UTC = "UTC";
	private static final int TIMEOUT = 60;

	private Long timestamp;

	public ValidTimestamp(Long timestamp) {
		this.timestamp = validate( timestamp );
	}

	public Long get() {
		return this.timestamp;
	}

	public boolean isValid() {
		return Objects.nonNull( this.timestamp );
	}

	private Long validate(Long timestamp) {
		if (Objects.isNull( timestamp ))
			throw new InvalidTimestampException();
		if (getLocalDateTime( timestamp ).isAfter( timeout() ))
			throw new TransactionTimeoutException( timestamp );
		return timestamp;
	}

	private LocalDateTime getLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant( Instant.ofEpochMilli( timestamp ), ZoneId.of( UTC ) );
	}

	private LocalDateTime timeout() {
		return LocalDateTime.now().plus( TIMEOUT, SECONDS );
	}
}
