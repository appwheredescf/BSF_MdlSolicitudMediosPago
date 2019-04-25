package mx.appwhere.mediospago.front.domain.repositories;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.domain.entities.EtlCamposArchivoEntity;

import java.util.Optional;

public interface EtlCamposArchivoRepository extends GenericRepository<EtlCamposArchivoEntity, Long> {

    Optional<EtlCamposArchivoEntity> findByNumeroCampoAndArchivoEntityCveArchivo(int campo, String claveArchivo);

}
