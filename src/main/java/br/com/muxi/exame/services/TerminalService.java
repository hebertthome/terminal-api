package br.com.muxi.exame.services;

import java.io.Serializable;

import br.com.muxi.exame.domains.Terminal;

public interface TerminalService extends Serializable {
	
	boolean exists(Integer logic);
	Terminal findByLogic(Integer logic);
	Terminal save(Terminal terminal);

}
