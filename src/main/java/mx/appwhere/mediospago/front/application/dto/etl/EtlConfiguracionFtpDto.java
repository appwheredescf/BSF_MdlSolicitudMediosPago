package mx.appwhere.mediospago.front.application.dto.etl;

import lombok.Data;

/**
 * Data transport object for EtlConfiguracionFtpEntity
 * @author JoseBarrios
 *
 */
@Data
public class EtlConfiguracionFtpDto {
    
    private Integer id;
    
    private String cveConfiguracion;

    private String direccionIp;

    private Integer puerto;

    private String password;

    private String usuario;
    
    private String directorio;
    
    private String prefijoDirectorio;
    
    private String nombreServidor;
}
