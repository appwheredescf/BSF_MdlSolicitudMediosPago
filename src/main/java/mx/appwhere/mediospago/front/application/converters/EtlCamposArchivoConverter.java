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
		target.setObligatorio(source.getObligatorio());
		target.setPosicionFinal(source.getPosicionFinal());
		target.setPosicionInicial(source.getPosicionInicial());
		target.setTipoDato(source.getCatTipoDato().getTipoDato());
		target.setValorDefault(source.getValorDefault());
		target.setNumeroCampo(source.getNumeroCampo());
		if (Objects.nonNull(source.getCampoArchivo())) {
		    target.setNombreCampo(source.getCampoArchivo().getNombreCampo());
		}
		if (source.getValorCampoArchivo() != null) {
			target.setValorCampoArchivo(convert(source.getValorCampoArchivo()));
		}
		if (source.getTipoRelleno() != null) {
			target.setTipoRelleno(EtlCatTipoRellenoConverter.convert(source.getTipoRelleno()));
		}
		if (source.getArchivoEntity() != null) {
			target.setIdArchivo(source.getArchivoEntity().getId());
			target.setCveArchivo(source.getArchivoEntity().getCveArchivo());
			target.setExtensionArchivo(source.getArchivoEntity().getExtension());
		}
		return target;
    }
    
    public static List<EtlCampoArchivoDto> convert(List<EtlCamposArchivoEntity> listSource) {
		List<EtlCampoArchivoDto> listTarget = new ArrayList<>();
		listSource.forEach(element -> listTarget.add(convert(element)));
		return listTarget;
    }
}
