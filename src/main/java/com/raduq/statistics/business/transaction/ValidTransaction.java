package com.raduq.statistics.business.transaction;

import java.util.Objects;

import com.raduq.statistics.business.transaction.exception.InvalidTransactionException;
import com.raduq.statistics.model.Transaction;

public class ValidTransaction {

	private Transaction transaction;

	public ValidTransaction(Transaction transaction) {
		this.transaction = validate( transaction );
	}

	private Transaction validate(Transaction transaction) {
		if (Objects.isNull( transaction )) {
			throw new InvalidTransactionException();
		}
		new ValidTimestamp( transaction.getTimestamp() ).isValid();

		return transaction;
	}

	public Transaction get() {
		return this.transaction;
	}
}
