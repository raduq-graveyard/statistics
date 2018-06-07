package com.raduq.statistics.service;

import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.raduq.statistics.data.Datastore;
import com.raduq.statistics.model.Statistics;

@Service
public class StatisticService {

	public Statistics get() {
		DoubleSummaryStatistics summarized = Datastore.getInstance().getData()
				.entrySet()
				.stream()
				.collect( Collectors.summarizingDouble( Map.Entry::getValue ) );
		return new Statistics( summarized );
	}
}
