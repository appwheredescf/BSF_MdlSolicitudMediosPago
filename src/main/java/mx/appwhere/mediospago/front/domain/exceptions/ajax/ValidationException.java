package mx.appwhere.mediospago.front.domain.exceptions.ajax;

import java.util.Map;

/**
 * @author Arturo Pérez
 * @version 1.0
 * Fecha 29/11/2017
 * Objeto para manejar las excepciones de las validaciones
 */
public class ValidationException {
    private Map<String, String> errores;

    public ValidationException(Map<String, String> errores){
        this.errores = errores;
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, String> errores) {
        this.errores = errores;
    }
}
