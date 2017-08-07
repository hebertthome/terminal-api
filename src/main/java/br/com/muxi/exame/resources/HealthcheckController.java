package br.com.muxi.exame.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckApi {
	
	@Autowired
	public HealthcheckApi() {
	}

	@GetMapping(path = "/healthcheck")
    public ResponseEntity<Object> get() {
    	return new ResponseEntity<Object>("WORKING", new HttpHeaders(), HttpStatus.OK);
    }
    
}
