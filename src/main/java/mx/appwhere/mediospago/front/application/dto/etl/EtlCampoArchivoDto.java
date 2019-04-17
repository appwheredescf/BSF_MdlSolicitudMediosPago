package mx.appwhere.mediospago.front.application.dto.etl;

import lombok.Data;

/**
 * Data transport object for EtlCampoArchivoEntity
 * @author JoseBarrios
 *
 */
@Data
public class EtlCampoArchivoDto {

    private Long id;
    
    private Long idArchivo;

    private String cveArchivo;

    private String extensionArchivo;
    
    private Integer posicionInicial;
    
    private Integer posicionFinal;
    
    private Boolean obligatorio;
    
    private String tipoDato;
    
    private String nombreCampo;
    
    private String descripcion;
    
    private String valorDefault;

    private EtlCampoArchivoDto valorCampoArchivo;

    private EtlCatTipoRellenoDto tipoRelleno;

    private Integer numeroCampo;
}
