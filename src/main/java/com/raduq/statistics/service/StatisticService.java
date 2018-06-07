package com.raduq.statistics.service;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.raduq.statistics.data.Datastore;
import com.raduq.statistics.model.Statistics;
import com.raduq.statistics.model.Transaction;

@Service
public class StatisticService {

	public Statistics get() {
		ConcurrentNavigableMap<Long, Double> data = Datastore.getInstance().getData();

		LocalDateTime now = LocalDateTime.now( ZoneOffset.UTC );
		Long from = now.minus( Transaction.TIMEOUT, SECONDS ).toInstant( ZoneOffset.UTC ).toEpochMilli();

		ConcurrentNavigableMap<Long, Double> filteredData = data
				.subMap( from, now.toInstant( ZoneOffset.UTC ).toEpochMilli() );

		DoubleSummaryStatistics summarized = filteredData
				.entrySet()
				.stream()
				.collect( Collectors.summarizingDouble( Map.Entry::getValue ) );
		return new Statistics( summarized );
	}
}
