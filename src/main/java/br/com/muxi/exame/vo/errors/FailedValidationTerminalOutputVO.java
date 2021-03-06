package br.com.muxi.exame.vo.errors;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class FailedValidationTerminalOutputVO implements Serializable {

    private static final long serialVersionUID = -7440122885223904782L;

    @JsonProperty("field")
    @ApiModelProperty(notes = "A field has an error occurred")
    private String field;

    @JsonProperty("summary")
    @ApiModelProperty(notes = "A summary of the literal problem")
    private String summary;

    public FailedValidationTerminalOutputVO() {
        super();
    }

    public FailedValidationTerminalOutputVO(String field, String summary) {
        super();
        this.field = field;
        this.summary = summary;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}