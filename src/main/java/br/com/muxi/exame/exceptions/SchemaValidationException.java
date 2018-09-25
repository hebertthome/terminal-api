package br.com.muxi.exame.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SchemaValidationException extends RuntimeException {

    private static final long serialVersionUID = -809358622857892552L;

    public SchemaValidationException() {
        super();
    }

    public SchemaValidationException(String s) {
        super(s);
    }

    public SchemaValidationException(Throwable t) {
        super(t);
    }

    public SchemaValidationException(String msg, Throwable t) {
        super(msg, t);
    }

}