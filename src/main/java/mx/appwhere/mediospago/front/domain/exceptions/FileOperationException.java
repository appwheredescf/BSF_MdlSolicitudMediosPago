package mx.appwhere.mediospago.front.domain.exceptions;


import java.text.MessageFormat;

/**
 *
 * @author JoseBarrios
 * @since 16/04/2018
 *
 */
public class FileOperationException extends Exception {

    public FileOperationException() {
        super();
    }

    public FileOperationException(String message, Object... args) {
        super(new MessageFormat(message).format(args));
    }

    public FileOperationException(Throwable cause) {
        super(cause);
    }

    public FileOperationException(String message,  Throwable cause) {
        super(message, cause);
    }

    public FileOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}