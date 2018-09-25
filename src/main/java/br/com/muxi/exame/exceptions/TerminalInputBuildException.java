package br.com.muxi.exame.exceptions;

public class TerminalInputBuildException extends Exception {

    private static final long serialVersionUID = 2187000559480622619L;

    public TerminalInputBuildException() {
        super();
    }

    public TerminalInputBuildException(String s) {
        super(s);
    }

    public TerminalInputBuildException(Throwable t) {
        super(t);
    }

    public TerminalInputBuildException(String msg, Throwable t) {
        super(msg, t);
    }

}