package br.com.muxi.exame.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.exceptions.TerminalSchemaValidatorException;
import br.com.muxi.exame.helpers.TerminalHLP;
import br.com.muxi.exame.resources.documenteded.TerminalController;
import br.com.muxi.exame.services.TerminalService;

public class TerminalControllerTest {
	
	private TerminalService service;
	private TerminalHLP helper;
	private MockMvc mockMvc;
	
	private final String terminalPath = "/terminal/";

	@Before
	public void setUp() {
		service = mock(TerminalService.class);
		helper = mock(TerminalHLP.class);
		TerminalController api = new TerminalController(service, helper);
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
	
	@Test(expected = NestedServletException.class)
	public void getFailed_InternalServerError() throws Exception {
		doThrow(new RuntimeException()).when(service).findByLogic(any(Integer.class));
		
		this.mockMvc.perform(get(terminalPath + any(Integer.class)));
	}
	
	@Test
	public void postSuccessful() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(helper.bindTerminal(any(String.class))).thenReturn(terminal);
		when(service.exists(any(Integer.class))).thenReturn(false);
		when(service.save(any(Terminal.class))).thenReturn(terminal);
		
		ResultActions result = 
			this.mockMvc.perform(post(terminalPath)
					.contentType("text/html")
					.content(getValidJson()));
		
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void postFailed_invalidInputValue() throws Exception {
		when(helper.bindTerminal(any(String.class))).thenThrow(new TerminalSchemaValidatorException());
		
		ResultActions result = 
			this.mockMvc.perform(post(terminalPath)
					.contentType("text/html")
					.content("someone_value"));
		
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void postFailed_invalidMediaType() throws Exception {
		when(helper.bindTerminal(any(String.class))).thenThrow(new TerminalSchemaValidatorException());
		
		ResultActions result = 
			this.mockMvc.perform(post(terminalPath)
					.contentType("application/json")
					.content(getValidJson()));
		
		result.andExpect(status().isUnsupportedMediaType());
	}
	
	@Test
	public void postFailed_alreadyExists() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(helper.bindTerminal(any(String.class))).thenReturn(terminal);
		when(service.exists(any(Integer.class))).thenReturn(true);
		
		ResultActions result = 
			this.mockMvc.perform(post(terminalPath)
					.contentType("text/html")
					.content(getValidJson()));
		
		result.andExpect(status().isConflict());
	}
	
	@Test(expected = NestedServletException.class)
	public void postFailed_errorOnSaveTerminal() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(helper.bindTerminal(any(String.class))).thenReturn(terminal);
		when(service.exists(any(Integer.class))).thenReturn(false);
		when(service.save(any(Terminal.class))).thenThrow(new DataIntegrityViolationException("someone_value"));
		 
			this.mockMvc.perform(post(terminalPath)
					.contentType("text/html")
					.content(getValidJson()));
	}
	
	
	@Test
	public void putSuccessful() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(service.exists(any(Integer.class))).thenReturn(true);
		when(service.save(any(Terminal.class))).thenReturn(terminal);
		
		ResultActions result = 
			this.mockMvc.perform(put(terminalPath + 1)
					.contentType("application/json")
					.content(getValidJson()));
		
		result.andExpect(status().isOk());
	}
	
	@Test
	public void putFailed_differentValues() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(service.exists(any(Integer.class))).thenReturn(true);
		when(service.save(any(Terminal.class))).thenReturn(terminal);
		
		ResultActions result = 
			this.mockMvc.perform(put(terminalPath + 2)
					.contentType("application/json")
					.content(getValidJson()));
		
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void putFailed_bodyError() throws Exception {
		ResultActions result = 
			this.mockMvc.perform(put(terminalPath + 1)
					.contentType("application/json")
					.content("someone_value"));
		
		result.andExpect(status().isBadRequest());
	}
	
	@Test
	public void putFailed_TerminalNotFounded() throws Exception {
		Terminal terminal = generateTerminal(1);
		when(service.exists(any(Integer.class))).thenReturn(false);
		when(service.save(any(Terminal.class))).thenReturn(terminal);
		
		ResultActions result = 
			this.mockMvc.perform(put(terminalPath + 1)
					.contentType("application/json")
					.content(getValidJson()));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test(expected = NestedServletException.class)
	public void putFailed_errorOnSaveTerminal() throws Exception {
		when(service.exists(any(Integer.class))).thenReturn(true);
		when(service.save(any(Terminal.class))).thenThrow(new DataIntegrityViolationException("someone_value"));
		
			this.mockMvc.perform(put(terminalPath + 1)
					.contentType("application/json")
					.content(getValidJson()));
	}
	
	private Terminal generateTerminal(Integer logic) {
		return new Terminal(logic, "123", "PWWIN", 0, "F04A2E4088B", 4,
				"8.00b3", 0, 16777216, "PWWIN");
	}
	
	private String getValidJson() {
		return "{\"logic\": 1, \"serial\": \"123\","
				+ "\"model\": \"PWWIN\",\"sam\": 0,\"ptid\": \"F04A2E4088B\","
				+ "\"plat\": 4,\"version\": \"8.00b3\","
				+ "\"mxr\": 0,\"mxf\": 16777216,\"VERFM\": \"PWWIN\"}";
	}

}
