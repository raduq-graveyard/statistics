package com.raduq.statistics.exception;

public class TransactionTimeoutException extends RuntimeException {

	private static final String MESSAGE_TEMPLATE = "The transaction's timestamp %s is older than 60 seconds";

	public TransactionTimeoutException(Long timestamp) {
		super( String.format( MESSAGE_TEMPLATE, timestamp ) );
	}
}
