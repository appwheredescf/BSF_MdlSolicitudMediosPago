package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Class for database table: bsf_etl_cat_tipo_dato
 * 
 * @author JoseBarrios
 *
 */
@Entity
@Table(name = "bsf_etl_cat_tipo_dato")
public class EtlCatTipoDatoEntity extends AbstractDomainEntity<Short> {

    private String tipoDato;

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }
}
