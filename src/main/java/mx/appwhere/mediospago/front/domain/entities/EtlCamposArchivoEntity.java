package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * Class for database table: bsf_etl_campos_archivo
 * 
 * @author JoseBarrios
 *
 */
@Entity
@Table(name = "bsf_etl_campos_archivo")
public class EtlCamposArchivoEntity extends AbstractDomainEntity<Long> {
    
    private Long idArchivo;
    
    
    private EtlCatCamposArchivoEntity campoArchivo;
    
    private Integer posicionInicial;
    
    private Integer posicionFinal;
    
    private Boolean obligatorio;
    
    private String tipoDato;

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public EtlCatCamposArchivoEntity getCampoArchivo() {
        return campoArchivo;
    }

    public void setCampoArchivo(EtlCatCamposArchivoEntity campoArchivo) {
        this.campoArchivo = campoArchivo;
    }

    public Integer getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(Integer posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public Integer getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(Integer posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public Boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }
}
