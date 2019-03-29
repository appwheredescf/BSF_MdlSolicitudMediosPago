package mx.appwhere.mediospago.front.application.dto.backend;

import java.time.ZonedDateTime;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/09
 */
public class ApiError {
    /**
     * Time of the exception.
     */
    private ZonedDateTime timestamp;

    /**
     * Http status code, , this property is automatically
     * set when {@code httpStatus} is established.
     */
    private Integer status;

    /**
     * Domain error name.
     */
    private String error;

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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
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
