package com.raduq.statistics.service;

import org.springframework.stereotype.Service;

import com.raduq.statistics.model.Event;
import com.raduq.statistics.data.Datastore;
import com.raduq.statistics.model.Transaction;

@Service
public class TransactionService {

	public void save(Event event) {
		Transaction transaction = new Transaction( event );
		Datastore.getInstance().accept( transaction.getTimestamp(), transaction.getAmount() );
	}

}
