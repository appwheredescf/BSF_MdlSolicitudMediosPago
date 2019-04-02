package mx.appwhere.mediospago.front.domain.repositories;

import java.util.Optional;

import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;

public interface EtlProcesoRepository extends GenericRepository<EtlProcesoEntity, Long> {
    
    Optional<EtlProcesoEntity> findByCveProceso(String folio);
    
}
