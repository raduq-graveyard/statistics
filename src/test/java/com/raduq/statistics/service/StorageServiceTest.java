package com.raduq.statistics.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

	@InjectMocks
	private StorageService service;

	@Before
	public void setup() throws Exception {
		service.init();
	}

	@Test
	public void canAccept() {
		service.accept( Instant.now().toEpochMilli(), 10.0D );

		Assert.assertThat( service.getData().size(), equalTo( 1 ) );
	}
}
