package com.raduq.statistics.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.raduq.statistics.model.Event;
import com.raduq.statistics.model.Statistics;
import com.raduq.statistics.model.Transaction;

@Service
public class TransactionService {

	private Statistics statistics;

	@PostConstruct
	public void post() {
		statistics = new Statistics();
	}

	public void save(Event event) {
		statistics.accept( new Transaction( event ) );
	}

}
