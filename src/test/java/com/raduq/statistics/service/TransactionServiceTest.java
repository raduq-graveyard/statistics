package com.raduq.statistics.service;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.CoreMatchers.equalTo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.raduq.statistics.data.Datastore;
import com.raduq.statistics.exception.TransactionTimeoutException;
import com.raduq.statistics.model.Event;

public class TransactionServiceTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void cantAcceptWhenOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );
		thrown.expectMessage( "The transaction's timestamp 1478192204000 is older than 60 seconds" );

		new TransactionService().save( new Event( 10.0D, 1478192204000L ) );

		Assert.assertTrue( Datastore.getInstance().getData().isEmpty() );
	}

	@Test
	public void cantAcceptWhenExactly1SecondOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );
		Long expiredTime = LocalDateTime.now().plus( 61, SECONDS ).toInstant( ZoneOffset.UTC ).toEpochMilli();

		new TransactionService().save( new Event( 10.0D, expiredTime ) );

		Assert.assertTrue( Datastore.getInstance().getData().isEmpty() );
	}

	@Test
	public void canAcceptNewerThanTimeout() {
		new TransactionService().save( new Event( 10.0D, new Date().getTime() ) );

		Assert.assertThat( Datastore.getInstance().getData().size(), equalTo( 1 ) );
	}

	@Test
	public void canGetData() {
		Assert.assertNotNull( Datastore.getInstance().getData() );
	}
}
