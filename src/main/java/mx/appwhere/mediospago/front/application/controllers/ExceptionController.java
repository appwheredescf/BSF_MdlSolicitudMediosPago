package mx.appwhere.mediospago.front.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.FormatException;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arturo Pérez
 * @version 1.0
 * Fecha 30/11/2017
 * Clase padre para todos los formularios que necesitan la validación de formularios
 */
@Controller
public class ExceptionController {

    /**
     * Nombre handleValidationException
     * Propósito Manejar las excepciones para responder a los errores de validación de formularios
     * @param exception Excepción con los detalles de los errores
     * @return ValidationException que contiene los detalles de los errores
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ValidationException handleValidationException(BindException exception) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> err = exception.getFieldErrors();
        if(!err.isEmpty()) {
            for (FieldError error : err) {
                if(error.getDefaultMessage() != null) {
                    if (error.getDefaultMessage().contains("java.lang.NumberFormatException")) {
                        errors.put(error.getField(), ApplicationConstants.FORMATO_NUMERO_MUY_LARGO);
                    }else {
                        errors.put(error.getField(), error.getDefaultMessage());
                    }
                }
            }
        }else{
            if(exception.getErrorCount() >= 1){
                List<ObjectError> errors1 = exception.getAllErrors();
                for(ObjectError error1: errors1){
                    errors.put(error1.getObjectName(), error1.getDefaultMessage());
                }
            }
        }
        return new ValidationException(errors);
    }

    @ExceptionHandler(FormatException.class)
    @ResponseBody
    public ValidationException handleReportException(FormatException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(exception.getMessage(), exception.getField());
        return new ValidationException(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ValidationException handleTypeMismatchException(MethodArgumentTypeMismatchException exception){
        Map<String, String> errors = new HashMap<>();
        errors.put(exception.getName(), "Debe ser de tipo " + exception.getRequiredType().getName());
        return new ValidationException(errors);
    }
}
