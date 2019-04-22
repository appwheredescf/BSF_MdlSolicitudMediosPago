package mx.appwhere.mediospago.front.application.scheduled;

import java.io.File;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import mx.appwhere.mediospago.front.application.util.FileProcessor;
import mx.appwhere.mediospago.front.domain.util.Util;
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
import mx.appwhere.mediospago.front.application.util.InFileProcessor;
import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;
import mx.appwhere.mediospago.front.domain.repositories.EtlProcesoRepository;
import mx.appwhere.mediospago.front.domain.services.FTPService;
import mx.appwhere.mediospago.front.domain.services.MediosPagoEntradaService;

import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.*;

/**
 * 
 * Class scheduled to execute payment means process
 * 
 * @author JoseBarrios
 *
 */
@Component
public class MediosPagoEntradaScheduled {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoEntradaScheduled.class);
    
    @Value("${scheduled.temporal.path}")
    private String temporaryDirectory;

    private MediosPagoEntradaService mediosPagoEntradaService;
    
    private EtlProcesoRepository etlProcesoRepository;
    
    private FTPService fTPService;

    private Util util;
    
    public MediosPagoEntradaScheduled(MediosPagoEntradaService mediosPagoEntradaService,
									  EtlProcesoRepository etlProcesoRepository,
									  FTPService fTPService,
									  Util util) {
    	this.mediosPagoEntradaService = mediosPagoEntradaService;
		this.etlProcesoRepository = etlProcesoRepository;
		this.fTPService = fTPService;
		this.util = util;
    }

	@Scheduled(cron = "${scheduled.cron-expression.medios-pago.entrada}")
    public void ejecutarProceso() {

    	File directorioTemporal = new File(temporaryDirectory);

    	if (directorioTemporal.exists()) {

			util.clearDirectory(temporaryDirectory);

			Optional<EtlProcesoEntity> optionalProceso = etlProcesoRepository.findByCveProceso(CVE_PROCESO_MEDIOS_PAGO_ENTRADA);

			if (optionalProceso.isPresent()) {

				EtlProcesoDto etlProcesoDto = EtlProcesoConverter.convert(optionalProceso.get());

				ArchivosFtpDto archivosFtpDto = fTPService.listDirectories(etlProcesoDto.getConfiguracionFtpEntrada());

				if (archivosFtpDto.getEstatus() == ApplicationConstants.OK) {

					int i = 0;
					while (i < archivosFtpDto.getListaArchivosFtpProcesar().size()) {

						procesarDirectorio(etlProcesoDto, archivosFtpDto.getListaArchivosFtpProcesar().get(i));

						i++;
					}
				} else {
					LOGGER.info(archivosFtpDto.getMensaje());
				}
			}
		} else {
    		LOGGER.error(ApplicationConstants.ETL_ERR_NO_DIRECTORIO, directorioTemporal.getName());
		}
    }
    
    private void procesarDirectorio(EtlProcesoDto etlProcesoDto, FTPFile ftpFile) {
	
		List<ResGralDto> lstErroresDto = new ArrayList<>();
		
		LOGGER.info("Inicio de procesamiento de directorio {}", ftpFile.getName());

		/** Copiamos el directorio FTP a procesar a un directory local **/
		File tempDirectory = fTPService.ftpToLocalDirectory(temporaryDirectory, etlProcesoDto.getConfiguracionFtpEntrada(), ftpFile.getName());

		/** Obtenemos los archivos correspondientes al proceso del directorio temporal **/
		Map<String, FileProcessor> mapFileProcessor =  fTPService.obtenerArchivosProceso(tempDirectory, etlProcesoDto.getArchivosProceso());

		try {

			/** Validamos el nombre de los archivos */
			lstErroresDto.addAll(mediosPagoEntradaService.validarNombresArchivos(mapFileProcessor));

			/** Si no existen errores */
			if (lstErroresDto.isEmpty()) {

				InFileProcessor archivoPrincipalFile = (InFileProcessor) mapFileProcessor.get(TIPO_ARCHIVO_CPRINCIPAL);
				Long nRegistros = archivoPrincipalFile.countLines();

				/** Realizamos validacion de archivo cifras control */
				lstErroresDto.addAll(mediosPagoEntradaService.validarCifrasControl(mapFileProcessor, nRegistros));

				/** Si no existen errores */
				if (lstErroresDto.isEmpty()) {

					FileProcessor archivoSalidaCuenta = mediosPagoEntradaService.crearArchivoSalidaCuenta(etlProcesoDto, tempDirectory);

					if (archivoSalidaCuenta != null) {

						/** Agregamos archivo de salida a Map */
						mapFileProcessor.put(archivoSalidaCuenta.getArchivoDto().getExtension(), archivoSalidaCuenta);

						/** Realizamos validacion de archivo principal **/
						lstErroresDto.addAll(mediosPagoEntradaService.validarArchivoPrincipalGenerarSalida(mapFileProcessor, tempDirectory));

					} else {
						lstErroresDto.add(util.crearErrorDto(ETL_ERR_CREAR_ARCHIVO_CUENTA));
					}
				}
			}

		} catch (Exception e) {
			LOGGER. error(e.getMessage());
			lstErroresDto.add(util.crearErrorDto(ETL_ERR_ERROR_INESPERADO, e.getMessage()));
		}

		if (!lstErroresDto.isEmpty()) {

			LOGGER.info("Se encontraron {} errores ", lstErroresDto.size());

			/** Creacion de archivo de errores */
			mediosPagoEntradaService.crearArchivoErrores(lstErroresDto, tempDirectory);

			/** Eliminamos Archivo de cuentas */
			FileProcessor archivoSalidaCuenta = mapFileProcessor.get(TIPO_ARCHIVO_ALTACUENTA);
			if (archivoSalidaCuenta != null) {
				archivoSalidaCuenta.getFile().delete();
			}
		}

		LOGGER.info("Fin de procesamiento de directorio: {}", ftpFile.getName());
	}
}