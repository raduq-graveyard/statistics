package com.raduq.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raduq.statistics.model.Event;
import com.raduq.statistics.model.Transaction;

@Service
public class TransactionService {

	@Autowired
	private StorageService storageService;

	public void save(Event event) {
		Transaction transaction = new Transaction( event );
		storageService.accept( transaction.getTimestamp(), transaction.getAmount() );
	}

}
