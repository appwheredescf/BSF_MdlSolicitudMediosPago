package mx.appwhere.mediospago.front.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import mx.appwhere.mediospago.front.domain.constants.DomainError;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author Jenaro Bello
 * @version 1.0 - 2017/11/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxError {
    /**
     * Time of the exception.
     */
    private ZonedDateTime timestamp;

    /**
     * Http status.
     */
    @JsonIgnore
    private HttpStatus httpStatus;

    /**
     * Http status code, , this property is automatically
     * set when {@code httpStatus} is established.
     */
    private Integer status;

    /**
     * Domain error name.
     */
    private DomainError error;

    /**
     * Domain error code, this property is automatically
     * set when {@code error} is established.
     */
    private Integer errorCode;

    /**
     * Error message.
     */
    private String message;

    /**
     * Url of  the invoked service.
     */
    private String path;

    /**
     * Additional information about the error.
     */
    private Object details;

    // setters & getters

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Status is also set if the {@code httpStatus} is not null.
     *
     * @param httpStatus {@link HttpStatus}
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        if (Objects.nonNull(httpStatus)) {
            status = httpStatus.value();
        }
        this.httpStatus = httpStatus;
    }

    public Integer getStatus() {
        return status;
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

    public DomainError getError() {
        return error;
    }

    /**
     * Error code is also set if the {@code error} is not null.
     *
     * @param error {@link DomainError}
     */
    public void setError(DomainError error) {
        if (Objects.nonNull(error)) {
            this.errorCode = error.code();
        }
        this.error = error;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
