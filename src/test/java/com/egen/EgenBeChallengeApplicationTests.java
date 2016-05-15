package com.egen;

import com.egen.model.Metric;
import com.egen.service.MetricService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bson.types.ObjectId;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EgenBeChallengeApplication.class)
@WebAppConfiguration
public class EgenBeChallengeApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void validate_metrics_read() {
		try {
			mockMvc.perform(get("/metrics/read")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validate_metrics_readByRange() {
		try {
			mockMvc.perform(get("/metrics/readRange/1463327792333/1463327802612/")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validate_alerts_read() {
		try {
			mockMvc.perform(get("/alerts/read")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validate_alerts_readByRange() {
		try {
			mockMvc.perform(get("/alerts/readRange/1463327933887/1463327959172/")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validate_metrics_create() {
		Metric metric = new Metric();
        metric.setValue(154);
        metric.setTimeStamp(1463327802612l);
        metric.setId(new ObjectId("57389cd2e4b032494d65e59c"));

        ObjectWriter jacksonOW = new ObjectMapper().writer().withDefaultPrettyPrinter();


        try {
            mockMvc.perform(post("/metrics/create/").content(jacksonOW.writeValueAsString(metric)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Test
	public void contextLoads() {
	}

}
