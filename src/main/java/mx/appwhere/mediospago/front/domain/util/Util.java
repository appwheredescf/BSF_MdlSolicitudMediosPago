package mx.appwhere.mediospago.front.domain.util;

import java.util.ArrayList;

import mx.appwhere.mediospago.front.application.dto.ResGralDto;

public interface Util<T> {

    Object jsonToObject(T objectRes, String json, ArrayList<String> nodos);
    Object jsonToObject(T objectRes, String json);
    String objectToJson(Object object);
    void clearDirectory(String pathDirectory);
    ResGralDto crearErrorDto(String message, Object... args);
    public boolean hasSpecialCharacters(String strValue);
    
}
