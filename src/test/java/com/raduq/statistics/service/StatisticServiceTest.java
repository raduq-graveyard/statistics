package com.raduq.statistics.service;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.raduq.statistics.model.Statistics;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {

	@InjectMocks
	private StatisticService service;
	@Mock
	private StorageService storageService;

	@Test
	public void canGet() {
		ConcurrentNavigableMap<Long, Double> data = new ConcurrentSkipListMap<>();
		data.put( Instant.now().minus( 1, SECONDS ).toEpochMilli(), 10.0D );
		data.put( Instant.now().minus( 2, SECONDS ).toEpochMilli(), 2.0D );
		when( storageService.getData() ).thenReturn( data );

		Statistics statistics = service.get();

		Assert.assertThat( statistics.getSum(), equalTo( 12.0D ) );
		Assert.assertThat( statistics.getAvg(), equalTo( 6.0D ) );
		Assert.assertThat( statistics.getMax(), equalTo( 10.0D ) );
		Assert.assertThat( statistics.getMin(), equalTo( 2.0D ) );
		Assert.assertThat( statistics.getCount(), equalTo( 2L ) );
	}

	@Test
	public void canGetOnlyFilteredOnes() {
		ConcurrentNavigableMap<Long, Double> data = new ConcurrentSkipListMap<>();
		data.put( Instant.now().minus( 1, SECONDS ).toEpochMilli(), 10.0D );
		data.put( Instant.now().minus( 61, SECONDS ).toEpochMilli(), 10.0D );
		data.put( Instant.now().minus( 2, SECONDS ).toEpochMilli(), 2.0D );
		when( storageService.getData() ).thenReturn( data );

		Statistics statistics = service.get();

		Assert.assertThat( statistics.getSum(), equalTo( 12.0D ) );
		Assert.assertThat( statistics.getAvg(), equalTo( 6.0D ) );
		Assert.assertThat( statistics.getMax(), equalTo( 10.0D ) );
		Assert.assertThat( statistics.getMin(), equalTo( 2.0D ) );
		Assert.assertThat( statistics.getCount(), equalTo( 2L ) );
	}

	@Test
	public void canGetAllExpired() {
		ConcurrentNavigableMap<Long, Double> data = new ConcurrentSkipListMap<>();
		data.put( Instant.now().minus( 61, SECONDS ).toEpochMilli(), 10.0D );
		data.put( Instant.now().minus( 62, SECONDS ).toEpochMilli(), 10.0D );
		data.put( Instant.now().minus( 63, SECONDS ).toEpochMilli(), 2.0D );
		when( storageService.getData() ).thenReturn( data );

		Statistics statistics = service.get();

		Assert.assertThat( statistics.getSum(), equalTo( 0.0D ) );
		Assert.assertThat( statistics.getAvg(), equalTo( 0.0D ) );
		Assert.assertThat( statistics.getMax(), equalTo( Double.NEGATIVE_INFINITY ) );
		Assert.assertThat( statistics.getMin(), equalTo( Double.POSITIVE_INFINITY ) );
		Assert.assertThat( statistics.getCount(), equalTo( 0L ) );
	}
}
