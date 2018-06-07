package com.raduq.statistics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raduq.statistics.model.Event;
import com.raduq.statistics.service.TransactionService;

@RestController
@RequestMapping(value = "transactions")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity save(@Valid @RequestBody Event event) {
		service.save( event );
		return ResponseEntity.status( HttpStatus.CREATED ).build();
	}
}
