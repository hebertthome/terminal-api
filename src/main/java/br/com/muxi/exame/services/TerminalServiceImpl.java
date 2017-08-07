package br.com.muxi.exame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.repositories.TerminalRepository;

@Service
public class TerminalServiceImpl implements TerminalService {

	private static final long serialVersionUID = -4584813594694220497L;
	
	private final TerminalRepository personRepository;
	
	@Autowired
	public TerminalServiceImpl(TerminalRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Terminal findByLogic(Integer logic) {
		return personRepository.findOne(logic);
	}

	@Override
	public Terminal save(Terminal terminal) {
		return personRepository.saveAndFlush(terminal);
	}

}
