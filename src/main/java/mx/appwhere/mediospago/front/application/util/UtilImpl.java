package mx.appwhere.mediospago.front.application.util;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.scheduled.MediosPagoScheduled;
import mx.appwhere.mediospago.front.domain.util.Util;

@Component
public class UtilImpl<T> implements Util<T> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoScheduled.class);

    /**
     * Metodo utilitario para convertir un json a un objeto.
     * @param objectRes
     * @param json
     * @param nodos
     * @return
     */
    @Override
    public Object jsonToObject(T objectRes, String json, ArrayList<String> nodos) {
	try {
	    Gson gson = new Gson();
	    JSONParser parser = new JSONParser();
	    Object objRes = parser.parse(json);
	    JSONObject jsonObject = (JSONObject) objRes;
	    for (String nodo : nodos) {
		if (jsonObject.containsKey(nodo)) {
		    jsonObject = (JSONObject) jsonObject.get(nodo);
		}
	    }
	    String jsonResponse = jsonObject.toJSONString();
	    return gson.fromJson(jsonResponse, ((T) objectRes).getClass());
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * Metodo utilitario para convertir un json a un objeto.
     * @param objectRes
     * @param json
     * @return
     * @throws ParseException
     */
    @Override
    public Object jsonToObject(T objectRes, String json) {
	try {
	    Gson gson = new Gson();
	    JSONParser parser = new JSONParser();
	    Object objRes = parser.parse(json);
	    JSONObject jsonObject = (JSONObject) objRes;
	    String jsonResponse = jsonObject.toJSONString();
	    Object response = gson.fromJson(jsonResponse, ((T) objectRes).getClass());
	    return response;
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * Metodo utilitario para convertir un objeto a un json.
     * @param object
     * @return
     */
    @Override
    public String objectToJson(Object object) {
	Gson gson = new Gson();
	return gson.toJson(object);
    }
    
    @Override
    public void clearDirectory(String pathDirectory) {
	try {
	    FileUtils.cleanDirectory(new File(pathDirectory));
	} catch (IOException e) {
	    LOGGER.error("Error al vaciar directorio {}", pathDirectory, e);
	}
    }

    @Override
    public ResGralDto crearErrorDto(String message, Object... args) {

	String messageFormat = new MessageFormat(message).format(args);

	ResGralDto responseError = new ResGralDto();
	responseError.setEstatus(ApplicationConstants.ERR);
	responseError.setMensaje(messageFormat);
	return responseError;
    }
    
    @Override
    public boolean hasSpecialCharacters(String strValue) {
	return !strValue.matches("^[A-Z0-9_-]*$");
    }
}