package mx.appwhere.mediospago.front.application.util;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCatTipoRellenoDto;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import mx.appwhere.mediospago.front.domain.util.Util;
import org.springframework.util.StringUtils;

@Component
public class UtilImpl<T> implements Util<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilImpl.class);

	private static SimpleDateFormat formatoArchivoAP = new SimpleDateFormat("ddMMyyyy");
	private static SimpleDateFormat formatoArchivoCTA = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat formatoArchivoAltaCTA = new SimpleDateFormat("yyMMdd");

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
	public boolean isValidNAme(String fileName, String extension) {

		String regularExpresion = "^MED_SOL_CTA_TAR_TC[0-9]{2}[0-9]{1}[0-9]{2}_[a-zA-Z0-9]{4}_[0-9]{2}\\.extension$";
		regularExpresion = regularExpresion.replace("extension", extension);

		return fileName.matches(regularExpresion);
	}

	@Override
	public boolean hasSpecialCharacters(String strValue) {
		return !strValue.matches("^[A-Za-z0-9_\\- Ñ]*$");
	}

	@Override
	public boolean isNumeric(String strValue) {
		return strValue.matches("^[0-9]*$");
	}

	@Override
	public boolean hasLowerCase(String strValue) {
		return !strValue.toUpperCase().equals(strValue);
	}

	@Override
	public boolean isDateValid(String strFecha) {

		Date date = null;
		formatoArchivoAP.setLenient(false);
		try {
			date = formatoArchivoAP.parse(strFecha);
		} catch (Exception e) {
			LOGGER.info("Error al convertir la fecha", e);
		}
		return date != null;
	}

	@Override
	public String toDateCta(String fechaStr) {

		try {
			Date date = formatoArchivoAP.parse(fechaStr);
			fechaStr = formatoArchivoCTA.format(date);
		} catch (Exception e) {
			fechaStr = "";
			LOGGER.info("Error al convertir la fecha", e);
		}
		return fechaStr;
	}

	@Override
	public String ajustarTamanioCampo(EtlCatTipoRellenoDto tipoRellenoDto, String campo, int longitud) {
		StringBuilder sbBuilder = new StringBuilder(campo);
		if (tipoRellenoDto != null) {
			if (tipoRellenoDto.getIzquierda()) {
				while (sbBuilder.length() < longitud ) {
					sbBuilder.insert(0, tipoRellenoDto.getCaracter());
				}
			} else {
				while (sbBuilder.length() < longitud ) {
					sbBuilder.append(tipoRellenoDto.getCaracter());
				}
			}
		}
		return sbBuilder.toString();
	}

	@Override
	public String obtenerCampo(String registro, int... indices) {
		return registro.substring(indices[0] - 1, indices[1]);
	}

	@Override
	public int getLongitudCampo(EtlCampoArchivoDto campoArchivoDto) {
		return campoArchivoDto.getPosicionFinal() - campoArchivoDto.getPosicionInicial() + 1;
	}

	@Override
	public String getFechaAltaCuenta(Date date) {
		return formatoArchivoAltaCTA.format(date);
	}

	@Override
	public ResGralDto validarCampoArchivo(EtlCampoArchivoDto campoArchivoDto, String registro, int numeroRegistro, String tipoArchivo) {

		ResGralDto resGralDto = new ResGralDto();

		registro = registro.trim();

		if (StringUtils.isEmpty(registro) && campoArchivoDto.getObligatorio()) {
			resGralDto = crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_VACIO, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
		} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_NUMERICO)  && !isNumeric(registro)) {
			resGralDto = crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_NUMERICO, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
		} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_FECHA)  && !registro.equals("00000000") && !isDateValid(registro)) {
			resGralDto = crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_FECHA, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
		} else {
			resGralDto.setEstatus(ApplicationConstants.OK);
		}

		/** Validamos si el campo debe tener cierto valor por defecto **/
		if (resGralDto.getEstatus() == ApplicationConstants.OK && campoArchivoDto.getValorDefault() != null
				&& !campoArchivoDto.getValorDefault().equals(registro)) {
			/** Descomentar en caso de requerir validar que el valor default sea el que venga en el archivo
			 resGralDto = util.crearErrorDto(ApplicationConstants.ETL_ERR_VALOR_DEFAULT,
			 campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo, campoArchivoDto.getValorDefault()); */
		}

		return resGralDto;
	}
}