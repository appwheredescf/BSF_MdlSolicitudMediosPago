package mx.appwhere.mediospago.front.application.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.appwhere.mediospago.front.application.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.services.ValidadorArchivoService;
import mx.appwhere.mediospago.front.domain.util.Util;

import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.*;

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
		    
		    if (fileProcessor.getArchivoDto().getTipoArchivo() == ApplicationConstants.CAT_TIPO_ARCHIVO_ENTRADA
					&&  !validarNombreArchivo (fileProcessor.getFile().getName(), cveProceso, fileProcessor.getArchivoDto().getExtension())) {
		    	lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_NOMBRE_ARCHIVO, fileProcessor.getArchivoDto().getExtension()));
		    }
		});
    
    	return lstErrores;
    }

    private boolean validarNombreArchivo (String fileName, String cveProceso, String extension) {
	
		boolean isValid = Boolean.FALSE;
		
		if (cveProceso.equals(CVE_PROCESO_MEDIOS_PAGO)) {
		    
		    if (extension.equals(TIPO_ARCHIVO_TARJETAS) ) {
		    	isValid = Boolean.TRUE;
		    } else {
		    	isValid = util.isValidNAme(fileName, extension);
		    }
		}
		
		return isValid;
    }
    
    public List<ResGralDto> validarCifrasControl (Map<String, FileProcessor> mapFileProcess, Long nRegistrosAP) {
	
		List<ResGralDto> lstErrores = new ArrayList<>();
		
		InFileProcessor cifrasControlFile = (InFileProcessor)mapFileProcess.get(TIPO_ARCHIVO_CIFRAS_CONTROL);
		
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

				    long cantidadRegistrosCC = Long.parseLong(util.obtenerCampo(registro, ApplicationConstants.CC_CAMPO_TOTAL_REGISTROS));
				    
				    if (nRegistrosAP != cantidadRegistrosCC) {
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
		    	if (!util.hasLowerCase(registro)) {
					archivoDto.getListaCampos().forEach(campoArchivoDto -> {

						String campo = util.obtenerCampo(registro, campoArchivoDto);

						ResGralDto resGralDto = validarCampoArchivo(campoArchivoDto, campo, nregistro, archivoDto.getExtension());
						if (resGralDto.getEstatus() == ApplicationConstants.ERR) {
							lstErrores.add(resGralDto);
						}
					});
				} else {
					lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_MINUSCULAS, nregistro, archivoDto.getExtension()));
				}
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
		
		if (StringUtils.isEmpty(registro) && campoArchivoDto.getObligatorio()) {
		    resGralDto = util.crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_VACIO, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
		} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_NUMERICO)  && !util.isNumeric(registro)) {
		    resGralDto = util.crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_NUMERICO, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
		} else if (campoArchivoDto.getTipoDato().equals(ApplicationConstants.TIPO_DATO_FECHA)  && !registro.equals("00000000") && !util.isDateValid(registro)) {
		    resGralDto = util.crearErrorDto(ApplicationConstants.ETL_ERR_CAMPO_FECHA, campoArchivoDto.getNombreCampo(), numeroRegistro, tipoArchivo);
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

    @Override
    public List<ResGralDto> validarArchivoPrincipalGenerarSalida(Map<String, FileProcessor> mapFileProcess, File tempDirectory) {
	
		List<ResGralDto> lstErrores = new ArrayList<>();

		/** Obtenemos los archivos principal y de salida */
		InFileProcessor archivoPrincipal = (InFileProcessor)mapFileProcess.get(TIPO_ARCHIVO_CPRINCIPAL);
		OutFileProcessor archivoSalidaCuenta = (OutFileProcessor)mapFileProcess.get(TIPO_ARCHIVO_ALTACUENTA);
		InExcelFileProcessor archivoCuentasExcel = (InExcelFileProcessor)mapFileProcess.get(TIPO_ARCHIVO_TARJETAS);

		try {

			archivoPrincipal.open();
			archivoSalidaCuenta.open();
			/*archivoCuentasExcel.open();*/

		    while (archivoPrincipal.hasNext() && lstErrores.isEmpty()) {

		    	String registro = archivoPrincipal.getNextLine();

				lstErrores.addAll(validarCamposArchivo(registro, archivoPrincipal.getCurrentLine(), archivoPrincipal.getArchivoDto()));

				if (lstErrores.isEmpty()) {
					String registroArchivoCuenta = generarRegistroArchivoCuenta(archivoSalidaCuenta.getArchivoDto().getListaCampos(), registro);
					archivoSalidaCuenta.write(registroArchivoCuenta);

					//LOGGER.info(archivoCuentasExcel.getValueColumn(XLS_NUMERO_COLUMNA_TARJETA));
				}
		    }
    	} catch (IOException e) {
		    LOGGER.info(e.getMessage(), e);
		    lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_LECTURA_ARCHIVO, archivoPrincipal.getFile().getName()));
		} finally {
			archivoPrincipal.close();
			archivoSalidaCuenta.close();
			//archivoCuentasExcel.close();
		}
		
		return lstErrores;
    }

    private String generarRegistroArchivoCuenta(List<EtlCampoArchivoDto> listaCamposSalida, String registro) {

    	StringBuilder sbBuilder = new StringBuilder();
		listaCamposSalida.stream().forEach(campoArchivo -> {

    		String valorCampo = campoArchivo.getValorDefault();

    		/** El valor se obtendra de algun campo de algun archivo */
			if (valorCampo == null && campoArchivo.getValorCampoArchivo() != null) {
				valorCampo = util.obtenerCampo(registro, campoArchivo.getValorCampoArchivo());
			}

			valorCampo = ajustarCampo(campoArchivo, valorCampo);

			sbBuilder.append(util.ajustarTamanioCampo(campoArchivo.getTipoRelleno(), valorCampo, util.getLongitudCampo(campoArchivo)));
		});

		return sbBuilder.append("\n").toString();
	}


	private String ajustarCampo(EtlCampoArchivoDto campoArchivoDto, String valor) {

    	int numeroCampo = campoArchivoDto.getNumeroCampo();
    	int longitudCampo = util.getLongitudCampo(campoArchivoDto);

    	switch (numeroCampo) {
    		case ApplicationConstants.CTA_NUMERO_CAMPO_SEXO_MUJER:
				valor = valor.equals("M") ? "X" : " ";
    			break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_SEXO_HOMBRE:
				valor = valor.equals("H") ? "X" : " ";
				break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_FECHA_NACIMIENTO:
				valor = util.toDateCta(valor);
				break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_CODIGO_POSTAL:
			case ApplicationConstants.CTA_NUMERO_CAMPO_CODIGO_POSTAL_CLIENTE:
				if ("".equals(valor.trim()) || Integer.parseInt(valor) == 0) {
					valor = "99999";
				}
				break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_TELEFONO:
				valor = valor.replaceAll(" ", "0");
				break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_CALLE:
			case ApplicationConstants.CTA_NUMERO_CAMPO_COLONIA:
				if ("".equals(valor.trim())){
					valor = "SN";
				}
				break;
			case ApplicationConstants.CTA_NUMERO_CAMPO_NO_EXTERIOR:
				if ("".equals(valor.trim())){
					valor = "0";
				}
				break;
			default:
				if (valor == null) {
					valor = "";
				}
		}

		if (valor.length() > longitudCampo) {
			valor = valor.substring(0, longitudCampo);
		}

		return valor;
 	}
}