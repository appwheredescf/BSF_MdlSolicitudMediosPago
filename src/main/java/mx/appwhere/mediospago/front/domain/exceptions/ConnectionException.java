package mx.appwhere.mediospago.front.domain.exceptions;

import org.springframework.ui.Model;

/**
 * @author Jenaro Bello
 * @version 1.0 - 2017/10/18
 */
public class ConnectionException extends ViewException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(String message, String view) {
        super(message, view);
    }

    public ConnectionException(String message, String view, Throwable cause) {
        super(message, view, cause);
    }

    public ConnectionException(String message, Model model) {
        super(message, model);
    }

    public ConnectionException(String message, Model model, Throwable cause) {
        super(message, model, cause);
    }

    public ConnectionException(String message, String view, Model model) {
        super(message, view, model);
    }

    public ConnectionException(String message, String view, Model model, Throwable cause) {
        super(message, view, model, cause);
    }
}
