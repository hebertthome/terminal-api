package br.com.muxi.exame.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.repositories.TerminalRepository;

public class TerminalServiceTest {

	private TerminalRepository repositoryMock;
	private TerminalService service;

	@Before
	public void setUp() {
		repositoryMock = mock(TerminalRepository.class);
		service = new TerminalServiceImpl(repositoryMock);
	}

	@Test
	public void findByLogicSuccessful() {
		Terminal input = generateTerminal(1);
		when(repositoryMock.findOne(1)).thenReturn(input);		
		
		Terminal result = service.findByLogic(1);
		
		verify(repositoryMock, times(1)).findOne(any(Integer.class));
		assertThat(result, equalTo(input));
	}
	
	@Test
	public void findByLogicFailed_NotFound() {
		when(repositoryMock.findOne(1)).thenReturn(null);		
		
		Terminal result = service.findByLogic(1);
		
		verify(repositoryMock, times(1)).findOne(any(Integer.class));
		assertThat(result, equalTo(null));
	}
	
	@Test
	public void saveSuccessful() {
		Terminal input = generateTerminal(1);
		when(repositoryMock.saveAndFlush(input)).thenReturn(input);		
		
		Terminal result = service.save(input);
		
		verify(repositoryMock, times(1)).saveAndFlush(any(Terminal.class));
		assertThat(result, equalTo(input));
	}
	
	private Terminal generateTerminal(Integer logic) {
		return new Terminal(logic, "123", "PWWIN", 0, "F04A2E4088B", 4,
				"8.00b3", 0, 16777216, "PWWIN");
	}

}
