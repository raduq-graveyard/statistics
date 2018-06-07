package com.raduq.statistics.service;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.raduq.statistics.exception.TransactionTimeoutException;
import com.raduq.statistics.model.Event;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@InjectMocks
	private TransactionService service;
	@Mock
	private StorageService storageService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void cantAcceptWhenOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );
		thrown.expectMessage( "The transaction's timestamp 1478192204000 is older than 60 seconds" );

		service.save( new Event( 10.0D, 1478192204000L ) );

		Assert.assertTrue( new StorageService().getData().isEmpty() );
	}

	@Test
	public void cantAcceptWhenExactly1SecondOlderThanTimeout() {
		thrown.expect( TransactionTimeoutException.class );
		Long expiredTime = LocalDateTime.now().plus( 61, SECONDS ).toInstant( ZoneOffset.UTC ).toEpochMilli();

		service.save( new Event( 10.0D, expiredTime ) );

		Assert.assertTrue( new StorageService().getData().isEmpty() );
	}

	@Test
	public void canAcceptNewerThanTimeout() {
		service.save( new Event( 10.0D, Instant.now().toEpochMilli() ) );
	}

}
