package mx.appwhere.mediospago.front.application.dto.etl;

import java.util.List;

import lombok.Data;

/**
 * Data transport object for EtlProcesoEntity
 * @author JoseBarrios
 *
 */
@Data
public class EtlProcesoDto {
    
    private Long id;
    
    private String cveProceso;
    
    private String nombreProceso;
    
    private EtlConfiguracionFtpDto configuracionFtpEntrada;
    
    private EtlConfiguracionFtpDto configuracionFtpSalida;
    
    private List<EtlArchivoDto> archivosProceso;
}
