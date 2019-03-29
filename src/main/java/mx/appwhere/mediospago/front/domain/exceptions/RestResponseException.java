package mx.appwhere.mediospago.front.domain.exceptions;

import java.util.Optional;

import mx.appwhere.mediospago.front.application.dto.backend.ApiError;

/**
 * Exception for handle the {@link ApiError} received from the server.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/13
 */
public class RestResponseException extends DomainException {

    private ApiError apiError;

    public RestResponseException(String message, ApiError apiError, Throwable cause) {
        super(message, cause);
        this.apiError = apiError;
    }

    // setters & getters

    public Optional<ApiError> getApiError() {
        return Optional.ofNullable(apiError);
    }
}
