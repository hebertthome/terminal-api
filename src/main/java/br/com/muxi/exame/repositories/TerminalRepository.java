package br.com.muxi.exame.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.muxi.exame.domains.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer>, Serializable {

}
