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
import org.springframework.dao.DataIntegrityViolationException;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.repositories.TerminalRepository;

public class TerminalServiceTest {

    private TerminalRepository repositoryMock;
    private TerminalService service;

    @Before
    public void setUp() {
        repositoryMock = mock(TerminalRepository.class);
        service = new TerminalService(repositoryMock);
    }

    @Test
    public void existsSuccessful() {
        when(repositoryMock.exists(any(Integer.class))).thenReturn(true);

        boolean result = service.exists(any(Integer.class));

        verify(repositoryMock, times(1)).exists(any(Integer.class));
        assertThat(result, equalTo(true));
    }

    @Test
    public void findByLogicSuccessful() {
        Terminal input = generateTerminal(1);
        when(repositoryMock.findOne(any(Integer.class))).thenReturn(input);

        Terminal result = service.findByLogic(any(Integer.class));

        verify(repositoryMock, times(1)).findOne(any(Integer.class));
        assertThat(result, equalTo(input));
    }

    @Test
    public void findByLogicFailed_NotFound() {
        when(repositoryMock.findOne(any(Integer.class))).thenReturn(null);

        Terminal result = service.findByLogic(any(Integer.class));

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

    @Test(expected = DataIntegrityViolationException.class)
    public void saveFailed_() {
        Terminal input = generateTerminal(1);
        when(repositoryMock.saveAndFlush(input)).thenThrow(new DataIntegrityViolationException("someone_text"));

        service.save(input);
    }

    private Terminal generateTerminal(Integer logic) {
        Terminal terminal = new Terminal(logic, "123", "PWWIN", "8.00b3");
        terminal.setSam(0);
        terminal.setPtid("F04A2E4088B");
        terminal.setPlat(4);
        terminal.setMxr(0);
        terminal.setMxf(16777216);
        terminal.setVerfm("PWWIN");
        return terminal;
    }

}
