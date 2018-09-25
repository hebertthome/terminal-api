package br.com.muxi.exame.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -809358622857892552L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException(Throwable t) {
        super(t);
    }

    public BadRequestException(String msg, Throwable t) {
        super(msg, t);
    }

}