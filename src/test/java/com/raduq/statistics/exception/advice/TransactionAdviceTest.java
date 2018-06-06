package com.raduq.statistics.exception.advice;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionAdviceTest {

	@Test
	public void canHandleTransactionTimeout() {
		ResponseEntity response = new TransactionAdvice().handleTransactionTimeout();

		Assert.assertThat( response.getStatusCode(), equalTo( HttpStatus.NO_CONTENT ) );
	}

}
