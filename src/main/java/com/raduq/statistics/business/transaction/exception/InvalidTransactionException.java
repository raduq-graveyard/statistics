package com.raduq.statistics.business.transaction.exception;

public class InvalidTransactionException extends RuntimeException {

	private static final String MESSAGE = "No transaction was received";

	public InvalidTransactionException() {
		super( MESSAGE );
	}
}
