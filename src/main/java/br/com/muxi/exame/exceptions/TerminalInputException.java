package br.com.muxi.exame.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TerminalInputException extends RuntimeException {

    private static final long serialVersionUID = -809358622857892552L;

    public TerminalInputException() {
        super();
    }

    public TerminalInputException(String s) {
        super(s);
    }

    public TerminalInputException(Throwable t) {
        super(t);
    }

    public TerminalInputException(String msg, Throwable t) {
        super(msg, t);
    }

}