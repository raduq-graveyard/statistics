package com.raduq.statistics.service;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class StorageService {

	private ConcurrentNavigableMap<Long, Double> data;

	@PostConstruct
	public void init() {
		this.data = new ConcurrentSkipListMap<>();
	}

	public StorageService accept(Long timestamp, Double amount) {
		data.put( timestamp, amount );
		return this;
	}

	public ConcurrentNavigableMap<Long, Double> getData() {
		return this.data;
	}

}