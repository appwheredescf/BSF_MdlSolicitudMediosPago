package mx.appwhere.mediospago.front.domain.exceptions;

import org.springframework.ui.Model;

/**
 * @author Jenaro Bello
 * @version 1.0 - 2017/10/27
 */
public class BsfOperadorException extends ViewException {
    public BsfOperadorException(String message) {
        super(message);
    }

    public BsfOperadorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BsfOperadorException(String message, String view) {
        super(message, view);
    }

    public BsfOperadorException(String message, String view, Throwable cause) {
        super(message, view, cause);
    }

    public BsfOperadorException(String message, Model model) {
        super(message, model);
    }

    public BsfOperadorException(String message, Model model, Throwable cause) {
        super(message, model, cause);
    }

    public BsfOperadorException(String message, String view, Model model) {
        super(message, view, model);
    }

    public BsfOperadorException(String message, String view, Model model, Throwable cause) {
        super(message, view, model, cause);
    }
}
