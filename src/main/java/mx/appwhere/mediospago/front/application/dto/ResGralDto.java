package mx.appwhere.mediospago.front.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author JoseBarrios
 *
 */
@Data
@NoArgsConstructor
public class ResGralDto {
    
    public ResGralDto(int estatus, String mensaje) {
	this.estatus = estatus;
	this.mensaje = mensaje;
    }

    private String codigo;
    
    private String mensaje;
    
    private int estatus;
}