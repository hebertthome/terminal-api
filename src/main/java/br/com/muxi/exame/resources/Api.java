package br.com.muxi.exame.resources;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.services.TerminalService;

@RestController
@RequestMapping(value = "/terminal", produces = "*/*")
public class Api {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final TerminalService service;
    
    @Autowired
    public Api(TerminalService service) {
		this.service = service;
	}
    
	@GetMapping(path = "/{logic}")
    public ResponseEntity<Object> get(@PathVariable("logic") Integer logic) {
		Terminal result = service.findByLogic(logic);
		if (result == null) {
    		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<Object>(HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleInternalServerErrorException(Exception e) {
		String uuid = UUID.randomUUID().toString();
		log.error(uuid, e);
		return new ResponseEntity<Object>(
				"{\"error\": {\"code\": \"" + uuid + "\"}}", new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
