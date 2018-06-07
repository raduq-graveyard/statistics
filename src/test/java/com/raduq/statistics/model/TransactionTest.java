package com.raduq.statistics.model;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.raduq.statistics.exception.TransactionTimeoutException;

public class TransactionTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void cantBeOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );
		thrown.expectMessage( "The transaction's timestamp 1478192204000 is older than 60 seconds" );

		new Transaction( new Event( 10.0D, 1478192204000L ) );
	}

	@Test
	public void cantBeExactly1SecondOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );

		LocalDateTime now = LocalDateTime.now();

		new Transaction( new Event( 10.0D, now.minus( 61, SECONDS ).toInstant( ZoneOffset.UTC ).toEpochMilli() ) );
	}

	@Test
	public void canBeNewerThanTimeout() {
		new Transaction( new Event( 10.0D, new Date().getTime() ) );
	}
}
