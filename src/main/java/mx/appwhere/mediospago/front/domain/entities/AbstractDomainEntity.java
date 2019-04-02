package mx.appwhere.mediospago.front.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Abstract implementation of a {@link DomainEntity}.
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/09/22
 */
@MappedSuperclass
public class AbstractDomainEntity<ID extends Serializable> implements DomainEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    /**
     * Creation date for the entity
     */
    @Column(updatable = false)
    private LocalDateTime creadoEn;

    /**
     * Last modification date for the entity
     */
    private LocalDateTime actualizadoEn;

    /**
     * Update date fields before persistence
     */
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
	actualizadoEn = LocalDateTime.now();
        if (creadoEn == null) {
            creadoEn = LocalDateTime.now();
        }
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

}
