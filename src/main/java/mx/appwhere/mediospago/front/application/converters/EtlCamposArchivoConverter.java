package mx.appwhere.mediospago.front.application.converters;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.experimental.UtilityClass;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.entities.EtlCamposArchivoEntity;

/**
 * Class to convert EtlCamposArchivoEntity to Dto Object
 * 
 * @author JoseBarrios
 *
 */
@UtilityClass
public class EtlCamposArchivoConverter {

    public static EtlCampoArchivoDto convert(EtlCamposArchivoEntity source) {
	
	EtlCampoArchivoDto target = new EtlCampoArchivoDto();
	target.setId(source.getId());
	target.setIdArchivo(source.getIdArchivo());
	target.setObligatorio(source.getObligatorio());
	target.setPosicionFinal(source.getPosicionFinal());
	target.setPosicionInicial(source.getPosicionInicial());
	target.setTipoDato(source.getCatTipoDato().getTipoDato());
	if (Objects.nonNull(source.getCampoArchivo())) {
	    target.setDescripcion(source.getCampoArchivo().getDescripcion());
	    target.setNombreCampo(source.getCampoArchivo().getNombreCampo());
	}
	return target;
    }
    
    public static List<EtlCampoArchivoDto> convert(List<EtlCamposArchivoEntity> listSource) {
	List<EtlCampoArchivoDto> listTarget = new ArrayList<>();
	listSource.forEach(element -> listTarget.add(convert(element)));
	return listTarget;
    }
}
