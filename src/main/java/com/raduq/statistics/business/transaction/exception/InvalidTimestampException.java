package com.raduq.statistics.business.transaction.exception;

public class InvalidTimestampException extends RuntimeException {

	private static final String MESSAGE = "No timestamp was received in the transaction";

	public InvalidTimestampException() {
		super( MESSAGE );
	}
}
