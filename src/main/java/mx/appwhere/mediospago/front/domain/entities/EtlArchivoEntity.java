package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for database table: bsf_etl_archivo
 * 
 * @author JoseBarrios
 *
 */

@Entity
@Table(name = "bsf_etl_archivo")
public class EtlArchivoEntity extends AbstractDomainEntity<Long> {

    private Long idProceso;
    
    private String cveArchivo;
    
    private String extension;
    
    private Short tipoArchivo; 
    
    private Integer columnasArchivo;
    
    private Integer longitudLinea;
    
    private Short orden;

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public String getCveArchivo() {
        return cveArchivo;
    }

    public void setCveArchivo(String cveArchivo) {
        this.cveArchivo = cveArchivo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Short getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(Short tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Integer getColumnasArchivo() {
        return columnasArchivo;
    }

    public void setColumnasArchivo(Integer columnasArchivo) {
        this.columnasArchivo = columnasArchivo;
    }

    public Integer getLongitudLinea() {
        return longitudLinea;
    }

    public void setLongitudLinea(Integer longitudLinea) {
        this.longitudLinea = longitudLinea;
    }

    public Short getOrden() {
        return orden;
    }

    public void setOrden(Short orden) {
        this.orden = orden;
    }
}
