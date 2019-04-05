package mx.appwhere.mediospago.front.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
    
    @OrderBy("orden asc")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProceso", insertable = false, updatable = false)
    private List<EtlArchivoEntity> archivosProceso;
    
    @ManyToOne
    @JoinColumn(name = "idConfiguracionFtpEntrada", insertable = false, updatable = false)
    private EtlConfiguracionFtpEntity configuracionFtpEntrada;
    
    @ManyToOne
    @JoinColumn(name = "idConfiguracionFtpSalida", insertable = false, updatable = false)
    private EtlConfiguracionFtpEntity configuracionFtpSalida;

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

    public List<EtlArchivoEntity> getArchivosProceso() {
        return archivosProceso;
    }

    public void setArchivosProceso(List<EtlArchivoEntity> archivosProceso) {
        this.archivosProceso = archivosProceso;
    }

    public EtlConfiguracionFtpEntity getConfiguracionFtpEntrada() {
        return configuracionFtpEntrada;
    }

    public void setConfiguracionFtpEntrada(EtlConfiguracionFtpEntity configuracionFtpEntrada) {
        this.configuracionFtpEntrada = configuracionFtpEntrada;
    }

    public EtlConfiguracionFtpEntity getConfiguracionFtpSalida() {
        return configuracionFtpSalida;
    }

    public void setConfiguracionFtpSalida(EtlConfiguracionFtpEntity configuracionFtpSalida) {
        this.configuracionFtpSalida = configuracionFtpSalida;
    }
}
