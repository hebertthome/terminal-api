package br.com.muxi.exame.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR) 
public class JsonException extends RuntimeException {
	
	private static final long serialVersionUID = -809358622857892552L;

	public JsonException() {
		super();
	}

	public JsonException(String s) {
		super(s);
	}

	public JsonException(Throwable t) {
		super(t);
	}

	public JsonException(String msg, Throwable t) {
		super(msg, t);
	}

}