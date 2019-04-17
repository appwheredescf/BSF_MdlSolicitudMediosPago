package mx.appwhere.mediospago.front.application.converters;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.domain.entities.EtlArchivoEntity;

/**
 * Class to convert EtlArchivoEntity to Dto Object
 * 
 * @author JoseBarrios
 *
 */
@UtilityClass
public class EtlArchivoConverter {

    public static EtlArchivoDto convert(EtlArchivoEntity source) {
	
		EtlArchivoDto target = new EtlArchivoDto();
		target.setColumnasArchivo(source.getColumnasArchivo());
		target.setCveArchivo(source.getCveArchivo());
		target.setExtension(source.getExtension());
		target.setId(source.getId());
		target.setIdProceso(source.getIdProceso());
		target.setLongitudLinea(source.getLongitudLinea());
		target.setOrden(source.getOrden());
		target.setTipoArchivo(source.getTipoArchivo());
		target.setListaCampos(EtlCamposArchivoConverter.convert(source.getListaCampos()));
		return target;
    }

    public static List<EtlArchivoDto> convert(List<EtlArchivoEntity> source) {
		List<EtlArchivoDto> targetList = new ArrayList<>();
		source.forEach(element -> targetList.add(convert(element)));
		return targetList;
    }
}
