package com.raduq.statistics.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TransactionTimeoutExceptionTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void canThrowException() {
		thrown.expect( TransactionTimeoutException.class );
		thrown.expectMessage( "The transaction's timestamp 1478192204000 is older than 60 seconds" );

		throw new TransactionTimeoutException( 1478192204000L );
	}
}
