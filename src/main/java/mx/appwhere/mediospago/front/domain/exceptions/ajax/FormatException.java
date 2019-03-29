package mx.appwhere.mediospago.front.domain.exceptions.ajax;

/**
 * @author Arturo Pérez
 * @version 1.0
 * Fecha 30/11/2017
 * Clase que maneja las exceptions para el formato de los pathVariables
 */
public class FormatException extends Exception{
    private String field;
    private String message;
    public FormatException(String message, String field){
        super(message);
        this.message = message;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
