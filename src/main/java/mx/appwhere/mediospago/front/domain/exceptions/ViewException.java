package mx.appwhere.mediospago.front.domain.exceptions;

import org.springframework.ui.Model;

import mx.appwhere.mediospago.front.domain.constants.DomainConstants;

/**
 * Exception which contains a view for redirection.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/09
 */
public class ViewException extends DomainException {

    /**
     * View name to be returned by the exception handler.
     */
    private String view = DomainConstants.ERROR_VIEW_NAME;

    /**
     * Model to be returned with the view.
     */
    private Model model;

    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewException(String message, String view) {
        super(message);
        this.view = view;
    }

    public ViewException(String message, String view, Throwable cause) {
        super(message, cause);
        this.view = view;
    }

    public ViewException(String message, Model model) {
        super(message);
        this.model = model;
    }

    public ViewException(String message, Model model, Throwable cause) {
        super(message, cause);
        this.model = model;
    }

    public ViewException(String message, String view, Model model) {
        super(message);
        this.view = view;
        this.model = model;
    }

    public ViewException(String message, String view, Model model, Throwable cause) {
        super(message, cause);
        this.view = view;
        this.model = model;
    }

    // setters & getters

    public String getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
