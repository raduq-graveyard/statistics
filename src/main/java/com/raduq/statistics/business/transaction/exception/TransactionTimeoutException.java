package com.raduq.statistics.business.transaction.exception;

public class TransactionTimeoutException extends RuntimeException {

	private static final String MESSAGE_TEMPLATE = "The transaction's timeout %s is older than 60 seconds";

	public TransactionTimeoutException(Long timestamp) {
		super( String.format( MESSAGE_TEMPLATE, timestamp ) );
	}
}
