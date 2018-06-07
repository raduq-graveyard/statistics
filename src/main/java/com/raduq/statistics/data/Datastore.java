package com.raduq.statistics.data;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Datastore {

	private ConcurrentNavigableMap<Long, Double> data;

	public static Datastore getInstance() {
		return StaticStatistics.INSTANCE;
	}

	private Datastore() {
		this.data = new ConcurrentSkipListMap<>();
	}

	public Datastore accept(Long timestamp, Double amount) {
		data.put( timestamp, amount );
		return this;
	}

	public ConcurrentNavigableMap<Long, Double> getData() {
		return this.data;
	}

	private static class StaticStatistics {
		static final Datastore INSTANCE = new Datastore();
	}
}