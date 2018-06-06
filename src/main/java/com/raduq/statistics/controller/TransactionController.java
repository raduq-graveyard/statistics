package com.raduq.statistics.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raduq.statistics.model.Event;

@RestController
@RequestMapping(value = "transactions")
public class TransactionController {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity save(@Valid @RequestBody Event travel) {
		return ResponseEntity.ok( String.format( "%s - %s", travel.getAmount(), travel.getTimestamp() ) );
	}
}
