package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Class for database table: bsf_etl_servidor_ftp
 * 
 * @author JoseBarrios
 * 
 */
@Entity
@Table(name = "bsf_etl_servidor_ftp")
public class EtlServidorFtpEntity extends AbstractDomainEntity<Integer> {

    private String nombre;

    private String descripcion;

    private String direccionIp;

    private Integer puerto;

    private String password;

    private String usuario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }
}
