package com.raduq.statistics.controller;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raduq.statistics.model.Event;
import com.raduq.statistics.service.TransactionService;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private TransactionService service;

	@Test
	public void canSave() throws Exception {

		Event event = new Event( 10.0D, Instant.now().toEpochMilli() );

		willDoNothing().given( service ).save( event );

		mvc.perform( post( "/transactions" )
				.content( objectMapper.writeValueAsString( event ) )
				.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( status().isCreated() );
	}
}