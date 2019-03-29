package mx.appwhere.mediospago.front.application.util;

import com.google.gson.Gson;

import mx.appwhere.mediospago.front.domain.util.Util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UtilImpl<T> implements Util<T> {

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
				if(jsonObject.containsKey(nodo)) {
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

}