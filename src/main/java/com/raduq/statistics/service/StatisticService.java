package com.raduq.statistics.service;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raduq.statistics.model.Statistics;
import com.raduq.statistics.model.Transaction;

/**
 * Service who handle statistics
 */
@Service
public class StatisticService {

	@Autowired
	private StorageService storageService;

	/**
	 * Returns statistics
	 */
	public Statistics get() {
		ConcurrentNavigableMap<Long, Double> data = storageService.getData();

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
