package mx.appwhere.mediospago.front.application.scheduled;

import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.CVE_PROCESO_MEDIOS_PAGO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.converters.EtlProcesoConverter;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlProcesoDto;
import mx.appwhere.mediospago.front.application.dto.ftp.ArchivosFtpDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;
import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;
import mx.appwhere.mediospago.front.domain.repositories.EtlProcesoRepository;
import mx.appwhere.mediospago.front.domain.services.FTPService;
import mx.appwhere.mediospago.front.domain.services.ValidadorArchivoService;
import mx.appwhere.mediospago.front.domain.util.Util;

/**
 * 
 * Class scheduled to execute payment means process
 * 
 * @author JoseBarrios
 *
 */
@Component
public class MediosPagoScheduled {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoScheduled.class);
    
    @Value("${temporal.path}")
    private String temporaryDirectory;
    
    private ValidadorArchivoService validadorArchivoService;
    
    private EtlProcesoRepository etlProcesoRepository;
    
    private FTPService fTPService;
    
    private Util<?> util;
    
    public MediosPagoScheduled( ValidadorArchivoService validadorArchivoService, EtlProcesoRepository etlProcesoRepository, FTPService fTPService, Util<?> util) {
		this.validadorArchivoService = validadorArchivoService;
		this.etlProcesoRepository = etlProcesoRepository;
		this.fTPService = fTPService;
		this.util = util;
    }
    
    @Scheduled(fixedRate = 500000)
    public void executePaymentMethod() {
	
		util.clearDirectory(temporaryDirectory);
		
		Optional<EtlProcesoEntity> optionalProceso = etlProcesoRepository.findByCveProceso(CVE_PROCESO_MEDIOS_PAGO);
		
		if (optionalProceso.isPresent()) {
		   
		    EtlProcesoDto etlProcesoDto = EtlProcesoConverter.convert(optionalProceso.get());
		    
		    ArchivosFtpDto archivosFtpDto = fTPService.listDirectories(etlProcesoDto.getConfiguracionFtpEntrada());
		    
		    if (archivosFtpDto.getEstatus() == ApplicationConstants.OK) {
				
				int i = 0;
				while(i < archivosFtpDto.getListaArchivosFtpProcesar().size()) {
				    
				    procesarDirectorio(etlProcesoDto, archivosFtpDto.getListaArchivosFtpProcesar().get(i));
				    
				    i++;
				}
		    } else {
		    	LOGGER.info(archivosFtpDto.getMensaje());
		    }
		}
    }
    
    private void procesarDirectorio(EtlProcesoDto etlProcesoDto, FTPFile ftpFile) {
	
		List<ResGralDto> lstErroresDto = new ArrayList<>();
		
		LOGGER.info("Inicio de procesamiento de archivo {}", ftpFile.getName());
		
		/** Copiamos el directorio FTP a procesar a un directory local **/
		File tempDirectory = fTPService.ftpToLocalDirectory(temporaryDirectory, etlProcesoDto.getConfiguracionFtpEntrada(), ftpFile.getName());
		
		/** Obtenemos los archivos correspondientes al proceso del directorio temporal **/
		Map<String, FileProcessor> mapFileProcessor =  FileProcessor.getFilesProcess(tempDirectory, etlProcesoDto.getArchivosProceso());
		
		/** Validamos el nombre de los archivos */
		lstErroresDto.addAll(validadorArchivoService.validarNombresArchivos(mapFileProcessor, etlProcesoDto.getCveProceso()));
		
		/** Si no existen errores */
		if (lstErroresDto.isEmpty()) {
		    
		    /** Realizamos validacion de archivo cifras control */
		    lstErroresDto.addAll(validadorArchivoService.validarCifrasControl(mapFileProcessor));
		    
		    /** Si no existen errores */
		    if (lstErroresDto.isEmpty()) {
			   /** Realizamos validacion de archivo principal **/
			   lstErroresDto.addAll(validadorArchivoService.validarArchivoPrincipal(mapFileProcessor));
		    }
		    
		} 
		
		LOGGER.info("Fin de procesamiento de archivo: {}, se encontraron los siguientes errorres: {}", ftpFile.getName(), lstErroresDto.toString());
	
    }
}
