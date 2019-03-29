package mx.appwhere.mediospago.front.domain.util;

import java.util.ArrayList;

public interface Util<T> {

    Object jsonToObject(T objectRes, String json, ArrayList<String> nodos);
    Object jsonToObject(T objectRes, String json);
    String objectToJson(Object object);
}
