package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Class for database table: bsf_elt_cat_campos_archivo
 * 
 * @author JoseBarrios
 *
 */
@Entity
@Table(name = "bsf_elt_cat_campos_archivo")
public class EtlCatCamposArchivoEntity extends AbstractDomainEntity<Long> {

    private String nombreCampo;
    
    private String descripcion;

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
