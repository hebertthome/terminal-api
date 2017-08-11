package br.com.muxi.exame.resources.documenteded;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.muxi.exame.config.annotations.ApiVersion;
import br.com.muxi.exame.domains.Terminal;
import br.com.muxi.exame.exceptions.BadRequestException;
import br.com.muxi.exame.exceptions.TerminalSchemaValidatorException;
import br.com.muxi.exame.helpers.TerminalHLP;
import br.com.muxi.exame.services.TerminalService;
import br.com.muxi.exame.vo.errors.DefaultErrorAttributes;
import br.com.muxi.exame.vo.errors.FailedValidationErrorsVO;

@RestController
@RequestMapping(value = "/terminal")
@ApiVersion(1)
public class TerminalController {
	
	private final TerminalService service;
	
	private final TerminalHLP helper;
    
    @Autowired
    public TerminalController(TerminalService service, TerminalHLP helper) {
		this.service = service;
		this.helper = helper;
	}
    
    @ApiOperation(value = "Get a terminal by logic value", response = Terminal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Termial Not Founded", response = DefaultErrorAttributes.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DefaultErrorAttributes.class)})
	@GetMapping(path = "/{logic}")
    public ResponseEntity<Object> get(@PathVariable("logic") Integer logic) {
		Terminal result = service.findByLogic(logic);
		if (result == null) {
    		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<Object>(result, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Insert a new Terminal")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Invalid input value", response = FailedValidationErrorsVO.class),
            @ApiResponse(code = 409, message = "Termial already exists with logic(field) value"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DefaultErrorAttributes.class)})
	@PostMapping(consumes = "text/html")
    public ResponseEntity<Object> post(@RequestBody String body) throws TerminalSchemaValidatorException {
		Terminal terminal = helper.bindTerminal(body);
		if (service.exists(terminal.getLogic())) {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		}
		service.save(terminal);
    	return new ResponseEntity<Object>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Update a exists Terminal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request", response = DefaultErrorAttributes.class),
            @ApiResponse(code = 404, message = "Termial not founded"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = DefaultErrorAttributes.class)})
	@PutMapping(path = "/{logic}", consumes = "application/json")
    public ResponseEntity<Object> put(@Valid @RequestBody Terminal terminal, @PathVariable("logic") Integer logic)  {
		if (terminal.getLogic() != null && terminal.getLogic().intValue() != logic.intValue()) {
			throw new BadRequestException("Login value on body object is different of path value");
		}
		if (!service.exists(terminal.getLogic())) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		service.save(terminal);
    	return new ResponseEntity<Object>(HttpStatus.OK);
    }
	
	@ExceptionHandler(value = { TerminalSchemaValidatorException.class })
	public ResponseEntity<Object> handleInternalServerErrorException(TerminalSchemaValidatorException e) {
		return new ResponseEntity<Object>(e.getErrorsList(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

}
