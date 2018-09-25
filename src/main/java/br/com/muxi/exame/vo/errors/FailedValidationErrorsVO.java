package br.com.muxi.exame.vo.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class FailedValidationErrorsVO implements Serializable {
    
    private static final long serialVersionUID = -4567961890596346889L;
    
    @ApiModelProperty(notes = "A list of errors on validation json")
    @JsonProperty("errors")
    private List<FailedValidationTerminalOutputVO> errors = new ArrayList<>();

    public FailedValidationErrorsVO(List<FailedValidationTerminalOutputVO> errors) {
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
