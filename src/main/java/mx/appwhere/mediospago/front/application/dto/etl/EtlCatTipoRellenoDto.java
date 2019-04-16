package mx.appwhere.mediospago.front.application.dto.etl;

import lombok.Data;

/**
 * Data transport object for EtlCampoArchivoEntity
 * @author JoseBarrios
 *
 */

@Data
public class EtlCatTipoRellenoDto {

    private String caracter;

    private String descripcion;

    private Boolean izquierda;
}
