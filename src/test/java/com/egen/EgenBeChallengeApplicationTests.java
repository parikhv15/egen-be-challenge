package com.egen;

import com.egen.model.Alert;
import com.egen.model.Metric;
import com.egen.rules.MetricsRule;
import com.egen.service.AlertService;
import com.egen.service.MetricService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bson.types.ObjectId;
import org.easyrules.api.RulesEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EgenBeChallengeApplication.class)
@WebAppConfiguration
public class EgenBeChallengeApplicationTests {


	@Autowired
	private MetricService metricService;


	@Autowired
	private AlertService alertService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void check_alert_Overweight() {

		long timeStamp = 1463327802612l;

		Metric metric = new Metric();
		metric.setValue((int)(1.2 * MetricsRule.baseWeight));
		metric.setTimeStamp(timeStamp);


		metricService.createMetric(metric);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
				if (alert.getTimeStamp() == timeStamp && alert.getType().equals("OVER_WEIGHT")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}

	@Test
	public void check_alert_Underweight() {

		long timeStamp = 1463327802612l;

		Metric metric = new Metric();
		metric.setValue((int)(0.8 * MetricsRule.baseWeight));

		metric.setTimeStamp(timeStamp);


		metricService.createMetric(metric);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("UNDER_WEIGHT")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}



	@Test
	public void contextLoads() {
	}

}
