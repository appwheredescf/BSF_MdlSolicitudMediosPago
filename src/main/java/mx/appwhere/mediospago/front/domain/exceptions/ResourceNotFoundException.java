package mx.appwhere.mediospago.front.domain.exceptions;

import org.springframework.ui.Model;

/**
 * @author Jenaro Bello
 * @version 1.0 - 2017/10/17
 */
public class ResourceNotFoundException extends ViewException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message, String view) {
        super(message, view);
    }

    public ResourceNotFoundException(String message, String view, Throwable cause) {
        super(message, view, cause);
    }

    public ResourceNotFoundException(String message, Model model) {
        super(message, model);
    }

    public ResourceNotFoundException(String message, Model model, Throwable cause) {
        super(message, model, cause);
    }

    public ResourceNotFoundException(String message, String view, Model model) {
        super(message, view, model);
    }

    public ResourceNotFoundException(String message, String view, Model model, Throwable cause) {
        super(message, view, model, cause);
    }
}
