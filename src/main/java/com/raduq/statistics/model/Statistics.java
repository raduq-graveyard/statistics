package com.raduq.statistics.model;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Statistics {

	private ConcurrentNavigableMap<Long, Double> data;

	public Statistics() {
		this.data = new ConcurrentSkipListMap<>();
	}

	public Statistics accept(Transaction transaction) {
		Event event = transaction.get();
		data.put( event.getTimestamp(), event.getAmount() );
		return this;
	}

	public ConcurrentNavigableMap<Long, Double> getData() {
		return this.data;
	}
}