package mx.appwhere.mediospago.front.application.serviceimpl;

import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.CVE_PROCESO_MEDIOS_PAGO;
import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.TIPO_ARCHIVO_CIFRAS_CONTROL;
import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.TIPO_ARCHIVO_CPRINCIPAL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;
import mx.appwhere.mediospago.front.domain.services.ValidadorArchivoService;
import mx.appwhere.mediospago.front.domain.util.Util;

/**
 * 
 * 
 * @author JoseBarrios
 * @since 04/04/2019
 *
 */
@Service
public class ValidadorArchivoServiceImpl implements ValidadorArchivoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidadorArchivoServiceImpl.class);
    
    private Util<?> util;
    
    public ValidadorArchivoServiceImpl(Util<?> util) {
	this.util = util;
    }
    
    public List<ResGralDto> validarNombresArchivos(Map<String, FileProcessor> mapFileProcess, String cveProceso) {
	
	List<ResGralDto> lstErrores = new ArrayList<>();
	mapFileProcess.forEach((key, fileProcessor) -> {
	    
	    if (!validarNombreArchivo (fileProcessor.getFile().getName(), cveProceso, fileProcessor.getArchivoDto().getExtension())) {
		lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_NOMBRE_ARCHIVO, fileProcessor.getArchivoDto().getExtension()));
	    }
	});
    
    	return lstErrores;
    }

    private boolean validarNombreArchivo (String fileName, String cveProceso, String extension) {
	
	boolean isValid = Boolean.FALSE;
	
	if (cveProceso.equals(CVE_PROCESO_MEDIOS_PAGO)) {
	    
	    if (extension.equals("xls") ) {
		isValid = Boolean.TRUE;
	    } else if (fileName.length() == 35) {
		
		String expectedName = CVE_PROCESO_MEDIOS_PAGO + "_" + "SOL_CTA_TAR_TCXXXXX_MB01_02." + extension;
		String informacionAdicional = fileName.substring(18, 23);
		expectedName = expectedName.replace("XXXXX", informacionAdicional);
		
		if (expectedName.equals(fileName)) {
		    isValid = Boolean.TRUE;
		}		
	    }
	}
	
	return isValid;
    }
    
    public List<ResGralDto> validarCifrasControl (Map<String, FileProcessor> mapFileProcess) {
	
	List<ResGralDto> lstErrores = new ArrayList<>();
	
	FileProcessor cifrasControlFile = mapFileProcess.get(TIPO_ARCHIVO_CIFRAS_CONTROL);
	
	try {
	    cifrasControlFile.open();
	    
	    if (cifrasControlFile.hasNext()) {
		
		String registro = cifrasControlFile.getNextLine();
		
		lstErrores.addAll(validarCamposArchivo(registro, cifrasControlFile.getCurrentLine(), cifrasControlFile.getArchivoDto())); 
		
		if (cifrasControlFile.hasNext()) {
		    lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_REGISTROS_ARCHIVO, cifrasControlFile.getFile().getName(), 1));
		}
		
		/** Si no existen errores, verificamos que el archivo AP, contenga las lineas mencionadas en CC*/
		if (lstErrores.isEmpty()) {
		    
		    FileProcessor archivoPrincipalFile = mapFileProcess.get(TIPO_ARCHIVO_CPRINCIPAL);

		    long cantidadRegistrosAP = archivoPrincipalFile.countLines();
		    long cantidadRegistrosCC = Long.parseLong(obtenerCampo(registro, ApplicationConstants.CC_CAMPO_TOTAL_REGISTROS));
		    
		    if (cantidadRegistrosAP != cantidadRegistrosCC) {
			lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_LINEAS_AP));
		    }
		}
	    }
	    
	    cifrasControlFile.close();
	    
	} catch (IOException e) {
	    LOGGER.info(e.getMessage(), e);
	    lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_LECTURA_ARCHIVO, cifrasControlFile.getFile().getName()));
	}
	
	return lstErrores;
    }
    
    private List<ResGralDto> validarCamposArchivo(String registro, int nregistro,  EtlArchivoDto archivoDto) {
	
	List<ResGralDto> lstErrores = new ArrayList<>();
	
	if (archivoDto.getLongitudLinea() == registro.length()) {
	    
	    if(!util.hasSpecialCharacters(registro)) {
		archivoDto.getListaCampos().forEach(campoArchivoDto -> {
		    String campo = obtenerCampo(registro,  campoArchivoDto); 
		    validarCampoArchivo(campoArchivoDto, campo, nregistro, archivoDto.getExtension());
        	});
	    } else {
		lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_CARACTERES_ESP, nregistro, archivoDto.getExtension()));
	    }
	} else {
	    lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_LONGITUD_LINEA, nregistro, archivoDto.getExtension()));
	}
	
	return lstErrores;
    }
    
    private ResGralDto validarCampoArchivo(EtlCampoArchivoDto campoArchivoDto, String registro, int numeroRegistro, String tipoArchivo) {
	
	ResGralDto resGralDto = new ResGralDto();
	
	registro = registro.trim();
	
	if (StringUtils.isEmpty(registro)) {
	    util.crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_VACIO, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
	} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_CADENA)) {

	} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_NUMERICO)) {

	} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_FECHA)) {

	}
	return resGralDto;
    }
    
    
    private String obtenerCampo(String registro, EtlCampoArchivoDto campoArchivoDto) {
	return registro.substring(campoArchivoDto.getPosicionInicial() - 1, campoArchivoDto.getPosicionFinal());
    }
    
    private String obtenerCampo(String registro, int... indices) {
   	return registro.substring(indices[0] - 1, indices[1]);
    }
    
    
}