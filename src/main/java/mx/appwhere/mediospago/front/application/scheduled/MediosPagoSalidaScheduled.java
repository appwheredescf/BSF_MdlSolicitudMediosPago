package mx.appwhere.mediospago.front.application.scheduled;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.converters.EtlProcesoConverter;
import mx.appwhere.mediospago.front.application.dto.etl.EtlProcesoDto;
import mx.appwhere.mediospago.front.application.dto.ftp.ArchivosFtpDto;
import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;
import mx.appwhere.mediospago.front.domain.repositories.EtlProcesoRepository;
import mx.appwhere.mediospago.front.domain.services.FTPService;
import mx.appwhere.mediospago.front.domain.util.Util;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Optional;

import static mx.appwhere.mediospago.front.application.constants.ApplicationConstants.CVE_PROCESO_MEDIOS_PAGO_SALIDA;

/**
 *
 * Class scheduled to execute payment means process
 *
 * @author JoseBarrios
 * @since 22/04/2019
 *
 */
public class MediosPagoSalidaScheduled {

    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoSalidaScheduled.class);

    @Value("${scheduled.temporal.path}")
    private String temporaryDirectory;

    private EtlProcesoRepository etlProcesoRepository;

    private FTPService fTPService;

    private Util util;

    @Autowired
    public MediosPagoSalidaScheduled (EtlProcesoRepository etlProcesoRepository,
                                      FTPService fTPService,
                                      Util util) {
        this.etlProcesoRepository = etlProcesoRepository;
        this.fTPService = fTPService;
        this.util = util;

    }

    //@Scheduled(cron = "${scheduled.cron-expression.medios-pago.salida}")
    public void ejecutarProceso() {

        File directorioTemporal = new File(temporaryDirectory);

        if (directorioTemporal.exists()) {

            util.clearDirectory(temporaryDirectory);

            Optional<EtlProcesoEntity> optionalProceso = etlProcesoRepository.findByCveProceso(CVE_PROCESO_MEDIOS_PAGO_SALIDA);

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

        } else {
            LOGGER.error(ApplicationConstants.ETL_ERR_NO_DIRECTORIO, directorioTemporal.getName());
        }
    }

    private void procesarDirectorio(EtlProcesoDto etlProcesoDto, FTPFile ftpFile) {

    }

}
