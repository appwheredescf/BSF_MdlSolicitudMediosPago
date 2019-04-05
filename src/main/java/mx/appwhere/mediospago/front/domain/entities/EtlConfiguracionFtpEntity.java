package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * Class for database table: bsf_etl_configuracion_ftp
 * 
 * @author JoseBarrios
 * 
 */
@Entity
@Table(name = "bsf_etl_configuracion_ftp")
public class EtlConfiguracionFtpEntity extends AbstractDomainEntity<Integer> {
    
    private String cveConfiguracion;
    
    private String directorio;
    
    private String prefijoDirectorio;
    
    @ManyToOne
    @JoinColumn(name = "idServidorFtp", insertable = false, updatable = false)
    private EtlServidorFtpEntity servidorFtpEntity;

    public EtlServidorFtpEntity getServidorFtpEntity() {
        return servidorFtpEntity;
    }

    public void setServidorFtpEntity(EtlServidorFtpEntity servidorFtpEntity) {
        this.servidorFtpEntity = servidorFtpEntity;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getCveConfiguracion() {
        return cveConfiguracion;
    }

    public void setCveConfiguracion(String cveConfiguracion) {
        this.cveConfiguracion = cveConfiguracion;
    }

    public String getPrefijoDirectorio() {
        return prefijoDirectorio;
    }

    public void setPrefijoDirectorio(String prefijoDirectorio) {
        this.prefijoDirectorio = prefijoDirectorio;
    }
}
