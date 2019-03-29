package mx.appwhere.mediospago.front.domain.exceptions;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/09/22
 */
public class DomainException extends RuntimeException {

    /**
     * Basic domain exception construcor
     *
     * @param message error message
     */
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
