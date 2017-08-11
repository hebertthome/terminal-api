package br.com.muxi.exame.vo.errors;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FailedValidationErrorsVO {

	@ApiModelProperty(notes = "A list of errors on validation json")
	@JsonProperty("errors")
	private List<FailedValidationTerminalOutputVO> errors = new ArrayList<FailedValidationTerminalOutputVO>();

	public FailedValidationErrorsVO(
			List<FailedValidationTerminalOutputVO> errors) {
		super();
		this.errors = errors;
	}

	public List<FailedValidationTerminalOutputVO> getErrors() {
		return errors;
	}

	public void setErrors(List<FailedValidationTerminalOutputVO> errors) {
		this.errors = errors;
	}

}
