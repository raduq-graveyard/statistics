package com.raduq.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raduq.statistics.model.Statistics;
import com.raduq.statistics.service.StatisticService;

@RestController
@RequestMapping(value = "statistics")
public class StatisticController {

	@Autowired
	private StatisticService service;

	@GetMapping
	public ResponseEntity<Statistics> get() {
		return ResponseEntity.ok( service.get() );
	}
}
