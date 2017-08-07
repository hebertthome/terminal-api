package br.com.muxi.exame.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HealthcheckApiTest {
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		HealthcheckApi api = new HealthcheckApi();
		mockMvc = MockMvcBuilders.standaloneSetup(api).build();
	}

	@Test
	public void healthcheckSuccessful() throws Exception {
		ResultActions result = this.mockMvc.perform(get("/healthcheck"));
		result
			.andExpect(status().isOk())
			.andExpect(content().string("WORKING"));
	}

}
