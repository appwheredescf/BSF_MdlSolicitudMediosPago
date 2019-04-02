package mx.appwhere.mediospago.front.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Class for database table: bsf_etl_proceso
 * 
 * @author JoseBarrios
 *
 */
@Entity
@Table(name = "bsf_etl_proceso")
public class EtlProcesoEntity extends AbstractDomainEntity<Long> {

    private String cveProceso;
    
    private String nombreProceso;
    
    private String ftpIpServidor;
    
    private String ftpPuerto;
    
    private String ftpPassword;
    
    @OneToMany
    @JoinColumn(name = "idProceso", insertable = false, updatable = false)
    private List<EtlArchivoEntity> archivosProceso;

    public String getCveProceso() {
        return cveProceso;
    }

    public void setCveProceso(String cveProceso) {
        this.cveProceso = cveProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public String getFtpIpServidor() {
        return ftpIpServidor;
    }

    public void setFtpIpServidor(String ftpIpServidor) {
        this.ftpIpServidor = ftpIpServidor;
    }

    public String getFtpPuerto() {
        return ftpPuerto;
    }

    public void setFtpPuerto(String ftpPuerto) {
        this.ftpPuerto = ftpPuerto;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public List<EtlArchivoEntity> getArchivosProceso() {
        return archivosProceso;
    }

    public void setArchivosProceso(List<EtlArchivoEntity> archivosProceso) {
        this.archivosProceso = archivosProceso;
    }
    
}
