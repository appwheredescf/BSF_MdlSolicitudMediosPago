package mx.appwhere.mediospago.front.domain.exceptions.ajax;

import mx.appwhere.mediospago.front.application.dto.backend.ApiError;
import mx.appwhere.mediospago.front.domain.constants.DomainError;
import mx.appwhere.mediospago.front.domain.exceptions.DomainException;

/**
 * Exception for handle the {@link ApiError} received from the server.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/13
 */
public class AjaxException extends DomainException {

    /**
     * {@link DomainError} for the error code.
     */
    private DomainError domainError;

    /**
     * Exception details. When the exception is mapped to an
     * {@link ApiError}
     * the details objects is appended.
     */
    private Object details;

    public AjaxException(String message, DomainError domainError, Object details) {
        super(message);
        this.domainError = domainError;
        this.details = details;
    }

    public AjaxException(String message, DomainError domainError, Object details, Throwable cause) {
        super(message, cause);
        this.domainError = domainError;
        this.details = details;
    }

    public AjaxException(String message, DomainError domainError) {
        super(message);
        this.domainError = domainError;
    }

    public AjaxException(String message, DomainError domainError, Throwable cause) {
        super(message, cause);
        this.domainError = domainError;
    }

    public DomainError getDomainError() {
        return domainError;
    }

    public Object getDetails() {
        return details;
    }
}
