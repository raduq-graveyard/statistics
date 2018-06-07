package com.raduq.statistics.exception;

/**
 * When a Timestamp is too old to be used by the application
 */
public class TransactionTimeoutException extends RuntimeException {

	private static final String MESSAGE_TEMPLATE = "The transaction's timestamp %s is older than 60 seconds";

	public TransactionTimeoutException(Long timestamp) {
		super( String.format( MESSAGE_TEMPLATE, timestamp ) );
	}
}
