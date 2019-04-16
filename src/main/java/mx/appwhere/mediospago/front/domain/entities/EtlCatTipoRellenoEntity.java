package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * Class for database table: bsf_etl_cat_tipo_relleno
 *
 * @author JoseBarrios
 *
 */
@Entity
@Table(name = "bsf_etl_cat_tipo_relleno")
public class EtlCatTipoRellenoEntity extends AbstractDomainEntity<Integer> {

    private String caracter;

    private String descripcion;

    private Boolean izquierda;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Boolean izquierda) {
        this.izquierda = izquierda;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }
}
