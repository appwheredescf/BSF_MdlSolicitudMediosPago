package mx.appwhere.mediospago.front.application.dto.etl;

import java.util.List;

import lombok.Data;

/**
 * Data transport object for EtlArchivoEntity
 * @author JoseBarrios
 *
 */
@Data
public class EtlArchivoDto {

    private Long id;
    
    private Long idProceso;
    
    private String cveArchivo;
    
    private String extension;
    
    private Short tipoArchivo; 
    
    private Integer columnasArchivo;
    
    private Integer longitudLinea;
    
    private Short orden;
    
    private List<EtlCampoArchivoDto> listaCampos;
}
