package mx.appwhere.mediospago.front.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.AjaxError;
import mx.appwhere.mediospago.front.domain.exceptions.ViewException;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.AjaxException;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.ResourceNotFoundRestAjaxException;

import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/09
 */
@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AppExceptionHandler.class);

    /**
     * Handle all {@link ViewException view exceptions}.
     * <p>
     * Catch exception and redirect to the given view.
     *
     * @param ex {@link ViewException}
     * @return view
     */
    @ExceptionHandler({
            ViewException.class
    })
    protected ModelAndView handleViewException(ViewException ex) {
        LOGGER.error(ex.getMessage(), ex);
        if (Objects.nonNull(ex.getModel())) {
            ex.getModel().addAttribute(ApplicationConstants.VIEW_MESSAGE_NAME, ex.getMessage());
            return new ModelAndView(ex.getView(), ex.getModel().asMap());
        } else {
            ModelAndView mav = new ModelAndView(ex.getView());
            mav.addObject(ApplicationConstants.VIEW_MESSAGE_NAME, ex.getMessage());
            return mav;
        }
    }

    /**
     * Handle all {@link AjaxException view exceptions}.
     * <p>
     * Catch exception and return an {@link AjaxError} .
     *
     * @param ex {@link AjaxException}
     * @return view
     */
    @ExceptionHandler({
            AjaxException.class
    })
    protected ResponseEntity<AjaxError> handleAjaxException(AjaxException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        // TODO: Add api error to log
        AjaxError ajaxError = new AjaxError();
        ajaxError.setTimestamp(ZonedDateTime.now());
        ajaxError.setPath((String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST));
        ajaxError.setMessage(ex.getMessage());
        ajaxError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        ajaxError.setError(ex.getDomainError());
        ajaxError.setDetails(ex.getDetails());

        if (ex instanceof ResourceNotFoundRestAjaxException) {
            ajaxError.setHttpStatus(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ajaxError, new HttpHeaders(), ajaxError.getHttpStatus());
    }

    /**
     * Handle every exception and add to logger
     *
     * @param ex exception
     */
    @ExceptionHandler(Exception.class)
    protected void handleException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
    }
}
