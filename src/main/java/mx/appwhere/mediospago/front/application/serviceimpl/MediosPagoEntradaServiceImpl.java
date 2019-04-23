package mx.appwhere.mediospago.front.application.serviceimpl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlProcesoDto;
import mx.appwhere.mediospago.front.application.util.*;
import mx.appwhere.mediospago.front.domain.exceptions.FileOperationException;
import mx.appwhere.mediospago.front.domain.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.services.MediosPagoEntradaService;

/**
 * 
 * 
 * @author JoseBarrios
 * @since 04/04/2019
 *
 */
@Service
public class MediosPagoEntradaServiceImpl implements MediosPagoEntradaService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoEntradaServiceImpl.class);

	private ApplicationContext applicationContext;

    private Util util;

    @Autowired
    public MediosPagoEntradaServiceImpl(ApplicationContext applicationContext, Util util) {
		this.applicationContext = applicationContext;
    	this.util = util;
	}

    public List<ResGralDto> validarNombresArchivos(Map<String, FileProcessor> mapFileProcess) {

		List<ResGralDto> lstErrores = new ArrayList<>();

		mapFileProcess.forEach((key, fileProcessor) -> {
		    
		    if (fileProcessor.getArchivoDto().getTipoArchivo() == ApplicationConstants.CAT_TIPO_ARCHIVO_ENTRADA
					&&  !validarNombreArchivo (fileProcessor.getFile().getName(), fileProcessor.getArchivoDto().getExtension())) {
		    	lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_NOMBRE_ARCHIVO, fileProcessor.getArchivoDto().getExtension()));
		    }
		});
    
    	return lstErrores;
    }

    private boolean validarNombreArchivo (String fileName, String extension) {
	
		boolean isValid;

		if (extension.equals(ApplicationConstants.TIPO_ARCHIVO_TARJETAS) ) {
			isValid = Boolean.TRUE;
		} else {
			isValid = util.isValidNAme(fileName, extension);
		}
		return isValid;
    }
    
    public List<ResGralDto> validarCifrasControl (Map<String, FileProcessor> mapFileProcess, Long nRegistrosAP) {
	
		List<ResGralDto> lstErrores = new ArrayList<>();
		
		InFileProcessor cifrasControlFile = (InFileProcessor)mapFileProcess.get(ApplicationConstants.TIPO_ARCHIVO_CIFRAS_CONTROL);
		
		try {

			cifrasControlFile.open();
		    
		    if (cifrasControlFile.hasNext()) {

		    	/** tomamos el registro del archivo */
				String registro = cifrasControlFile.getNextLine();

				lstErrores.addAll(cifrasControlFile.validarCamposArchivo());
				
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
		    
		} catch (FileOperationException e) {
		    lstErrores.add(util.crearErrorDto(e.getMessage()));
		}
		
		return lstErrores;
    }

    @Override
    public List<ResGralDto> validarArchivoPrincipalGenerarSalida(Map<String, FileProcessor> mapFileProcess, File tempDirectory) {
	
		List<ResGralDto> lstErrores = new ArrayList<>();

		/** Obtenemos los archivos necesarios para el procesamiento */
		InFileProcessor archivoPrincipal = (InFileProcessor)mapFileProcess.get(ApplicationConstants.TIPO_ARCHIVO_CPRINCIPAL);
		OutFileProcessor archivoSalidaCuenta = (OutFileProcessor)mapFileProcess.get(ApplicationConstants.TIPO_ARCHIVO_ALTACUENTA);
		InExcelFileProcessor archivoCuentasExcel = (InExcelFileProcessor)mapFileProcess.get(ApplicationConstants.TIPO_ARCHIVO_TARJETAS);

		try {

			archivoPrincipal.open();
			archivoSalidaCuenta.open();
			archivoCuentasExcel.open();

		    while (archivoPrincipal.hasNext() && lstErrores.isEmpty()) {

		    	archivoPrincipal.getNextLine();
				archivoCuentasExcel.getNextLine();

				/** Validamos los campos del archivo principal */
				lstErrores.addAll(archivoPrincipal.validarCamposArchivo());

				/** Validamos los campos del archivo de tarjetas */
				lstErrores.addAll(archivoCuentasExcel.validarCamposArchivo());

				if (lstErrores.isEmpty()) {
					String registroArchivoCuenta = generarRegistroArchivoCuenta(archivoSalidaCuenta.getArchivoDto().getListaCampos(), mapFileProcess);
					archivoSalidaCuenta.write(registroArchivoCuenta);
				}
		    }
    	} catch (FileOperationException e) {
		    lstErrores.add(util.crearErrorDto(e.getMessage()));
		} finally {
			archivoPrincipal.close();
			archivoSalidaCuenta.close();
			archivoCuentasExcel.close();
		}
		
		return lstErrores;
    }

	/**************************************************************************************************************************
	 *
	 * @param listaCamposSalida
	 * @param mapFileProcess
	 * @return
	 *************************************************************************************************************************/
    private String generarRegistroArchivoCuenta(List<EtlCampoArchivoDto> listaCamposSalida, Map<String, FileProcessor> mapFileProcess) {

    	StringBuilder sbBuilder = new StringBuilder();
		listaCamposSalida.stream().forEach(campoArchivo -> {

    		String valorCampo = campoArchivo.getValorDefault();

    		/** El valor se obtendra de algun campo de algun archivo */
			if (valorCampo == null && campoArchivo.getValorCampoArchivo() != null) {
				FileProcessor fileProcessor = mapFileProcess.get(campoArchivo.getValorCampoArchivo().getExtensionArchivo());
				valorCampo = fileProcessor.obtenerCampo(campoArchivo.getValorCampoArchivo());
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

 	@Override
	public FileProcessor crearArchivoSalidaCuenta(EtlProcesoDto etlProcesoDto, File tempDirectory) {

		OutFileProcessor archivoSalidaCuenta = null;
		try {

			Optional<EtlArchivoDto> archivoOptional = etlProcesoDto.getArchivosProceso().stream().filter(etlArchivoDto ->
					etlArchivoDto.getExtension().equals(ApplicationConstants.TIPO_ARCHIVO_ALTACUENTA)
			).findFirst();

			if (archivoOptional.isPresent()) {

				String fileName = "AltaCtaF" + util.getFechaAltaCuenta(new Date()) + ".txt";

				/** Creamos bean para procesar archivo de salida Cuenta */
				archivoSalidaCuenta = (OutFileProcessor)applicationContext.getBean("outFileProcessor");
				archivoSalidaCuenta.setFile(new File(tempDirectory + File.separator + fileName));
				archivoSalidaCuenta.setArchivoDto(archivoOptional.get());

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return archivoSalidaCuenta;
	}

	@Override
	public File crearArchivoErrores(List<ResGralDto> lstErroresDto, File tempDirectory) {

		String fileName = "ErroresF" + util.getFechaAltaCuenta(new Date()) + ".txt";

    	/** Creamos bean para procesar archivo de salida Errores */
		OutFileProcessor archivoSalidaErrores = (OutFileProcessor)applicationContext.getBean("outFileProcessor");
		archivoSalidaErrores.setFile(new File(tempDirectory + File.separator + fileName));

		try {
			archivoSalidaErrores.open();
			lstErroresDto.stream().forEach(errorDto -> {
				try {
					archivoSalidaErrores.write(errorDto.getMensaje() + "\n");
				} catch (FileOperationException e) {
					LOGGER.error(e.getMessage(), e);
				}
			});
		} catch (FileOperationException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			archivoSalidaErrores.close();
		}

		return archivoSalidaErrores.getFile();
	}
}