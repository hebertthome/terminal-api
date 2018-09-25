package br.com.muxi.exame.vo.errors;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.springframework.validation.FieldError;

public class DefaultErrorAttributes {

    @ApiModelProperty(notes = "When the error occurred in timestamp format")
    private Long timestamp;

    @ApiModelProperty(notes = "The HTTP status error")
    private Integer status;

    @ApiModelProperty(notes = "When exception is type org.springframework.web.bind.MethodArgumentNotValidException")
    private List<FieldError> errors;

    @ApiModelProperty(notes = "A literal from http status error")
    private String error;

    @ApiModelProperty(notes = "A internal exception throwed")
    private String exception;

    @ApiModelProperty(notes = "A summary of the literal problem")
    private String message;

    @ApiModelProperty(notes = "The route called")
    private String path;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

}
