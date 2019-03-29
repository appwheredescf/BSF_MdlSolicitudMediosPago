package mx.appwhere.mediospago.front.domain.exceptions;

import org.springframework.ui.Model;

/**
 * @author Jose Angel Hernandez
 * @version 1.0 - 2018/02/20
 */
public class AplicationServiceException extends ViewException {
    public AplicationServiceException(String message) {
        super(message);
    }

    public AplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AplicationServiceException(String message, String view) {
        super(message, view);
    }

    public AplicationServiceException(String message, String view, Throwable cause) {
        super(message, view, cause);
    }

    public AplicationServiceException(String message, Model model) {
        super(message, model);
    }

    public AplicationServiceException(String message, Model model, Throwable cause) {
        super(message, model, cause);
    }

    public AplicationServiceException(String message, String view, Model model) {
        super(message, view, model);
    }

    public AplicationServiceException(String message, String view, Model model, Throwable cause) {
        super(message, view, model, cause);
    }
}
