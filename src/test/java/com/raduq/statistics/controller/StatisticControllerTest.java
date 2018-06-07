package com.raduq.statistics.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.raduq.statistics.model.Statistics;
import com.raduq.statistics.service.StatisticService;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticController.class)
public class StatisticControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private StatisticService service;

	@Test
	public void canGet() throws Exception {
		Statistics statistics = new Statistics();
		given( service.get() ).willReturn( statistics );

		mvc.perform( get( "/statistics" )
				.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( status().isOk() );
	}
}
