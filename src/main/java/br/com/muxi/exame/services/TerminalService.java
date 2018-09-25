package br.com.muxi.exame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.repositories.TerminalRepository;

@Service
public class TerminalService {

    private final TerminalRepository terminalRepository;

    @Autowired
    public TerminalService(final TerminalRepository personRepository) {
        this.terminalRepository = personRepository;
    }

    public boolean exists(Integer logic) {
        return terminalRepository.exists(logic);
    }

    public Terminal findByLogic(Integer logic) {
        return terminalRepository.findOne(logic);
    }

    public Terminal save(Terminal terminal) {
        return terminalRepository.saveAndFlush(terminal);
    }

}
