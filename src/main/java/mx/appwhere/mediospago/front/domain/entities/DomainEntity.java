package mx.appwhere.mediospago.front.domain.entities;

import java.io.Serializable;

/**
 * Represents a domain entity
 *
 * @author Alejandro Martin
 * @version 1.0 - 2017/09/22
 */
public interface DomainEntity<ID extends Serializable> {
    /**
     * Get id value of the entity
     *
     * @return entity identifier
     */
    ID getId();

    /**
     * Set id value for the entity
     *
     * @param id entity identifier
     */
    void setId(ID id);
}
