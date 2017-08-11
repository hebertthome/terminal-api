package br.com.muxi.exame.exceptions;

import br.com.muxi.exame.vo.errors.FailedValidationErrorsVO;

public class TerminalSchemaValidatorException extends Exception {
	
	private static final long serialVersionUID = -809358622857892552L;
	
	private FailedValidationErrorsVO errorsList;
	
	public TerminalSchemaValidatorException() {
		super();
	}

	public TerminalSchemaValidatorException(FailedValidationErrorsVO errorsList) {
		super();
		this.errorsList = errorsList;
	}

	public TerminalSchemaValidatorException(String s, FailedValidationErrorsVO errorsList) {
		super(s);
		this.errorsList = errorsList;
	}

	public FailedValidationErrorsVO getErrorsList() {
		return errorsList;
	}
	
}
