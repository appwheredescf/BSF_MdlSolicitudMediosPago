package mx.appwhere.mediospago.front.domain.util;

import java.util.ArrayList;
import java.util.Date;

import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCatTipoRellenoDto;

public interface Util<T> {

    Object jsonToObject(T objectRes, String json, ArrayList<String> nodos);

    Object jsonToObject(T objectRes, String json);

    String objectToJson(Object object);

    void clearDirectory(String pathDirectory);

    ResGralDto crearErrorDto(String message, Object... args);

    boolean isValidNAme(String fileName, String extension);

    boolean hasSpecialCharacters(String strValue);

    boolean isNumeric(String strValue);

    boolean hasLowerCase(String strValue);

    boolean isDateValid(String strFecha);

    String toDateCta(String fechaStr);

    String ajustarTamanioCampo(EtlCatTipoRellenoDto tipoRellenoDto, String campo, int longitud);

    String obtenerCampo(String registro, int... indices);

    int getLongitudCampo(EtlCampoArchivoDto campoArchivoDto);

    String getFechaAltaCuenta(Date date);

    ResGralDto validarCampoArchivo(EtlCampoArchivoDto campoArchivoDto, String registro, int numeroRegistro, String tipoArchivo);
}
