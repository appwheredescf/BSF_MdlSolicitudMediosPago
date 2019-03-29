package mx.appwhere.mediospago.front.domain.exceptions;

/**
 * Exception is thrown when the encryption service cannot perform an operation.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/16
 */
public class EncryptionServiceException extends DomainException {
    public EncryptionServiceException(String message) {
        super(message);
    }

    public EncryptionServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
