package br.com.muxi.exame.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.services.TerminalService;

public class ApiTest {
	
	private TerminalService service;
	private MockMvc mockMvc;
	
	private final String terminalPath = "/terminal/";

	@Before
	public void setUp() {
		service = mock(TerminalService.class);
		Api api = new Api(service);
		mockMvc = MockMvcBuilders.standaloneSetup(api).build();
	}

	@Test
	public void getSuccessful() throws Exception {
		when(service.findByLogic(any(Integer.class))).thenReturn(generateTerminal(1));
		
		ResultActions result = this.mockMvc.perform(get(terminalPath + any(Integer.class)));
		
		result.andExpect(status().isOk());
	}
	
	@Test
	public void getFailed_NotFound() throws Exception {
		when(service.findByLogic(any(Integer.class))).thenReturn(null);
		
		ResultActions result = this.mockMvc.perform(get(terminalPath + any(Integer.class)));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void getFailed_InternalServerError() throws Exception {
		doThrow(new RuntimeException()).when(service).findByLogic(any(Integer.class));
		
		ResultActions result = this.mockMvc.perform(get(terminalPath + any(Integer.class)));
		
		result.andExpect(status().isInternalServerError());
	}

	private Terminal generateTerminal(Integer logic) {
		return new Terminal(logic, "123", "PWWIN", 0, "F04A2E4088B", 4,
				"8.00b3", 0, 16777216, "PWWIN");
	}

}
