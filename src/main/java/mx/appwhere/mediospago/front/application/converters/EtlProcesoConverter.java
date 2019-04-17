package mx.appwhere.mediospago.front.application.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.experimental.UtilityClass;
import mx.appwhere.mediospago.front.application.dto.etl.EtlProcesoDto;
import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;

/**
 * Class to convert EtlProcesoEntity to Dto Object
 * 
 * @author JoseBarrios
 *
 */
@UtilityClass
public class EtlProcesoConverter{
    
    public static EtlProcesoDto convert(EtlProcesoEntity source) {
		EtlProcesoDto target = new EtlProcesoDto();
		target.setId(source.getId());
		target.setCveProceso(source.getCveProceso());
		target.setNombreProceso(source.getNombreProceso());
		target.setArchivosProceso(EtlArchivoConverter.convert(source.getArchivosProceso()));

		if (Objects.nonNull(source.getConfiguracionFtpEntrada())) {
			target.setConfiguracionFtpEntrada(EtlConfiguracionFtpConverter.convert(source.getConfiguracionFtpEntrada()));
		}
		if (Objects.nonNull(source.getConfiguracionFtpSalida())) {
			target.setConfiguracionFtpSalida(EtlConfiguracionFtpConverter.convert(source.getConfiguracionFtpSalida()));
		}
		return target;
    }

    public static List<EtlProcesoDto> convert(List<EtlProcesoEntity> source) {
		List<EtlProcesoDto> targetList = new ArrayList<>();
		source.forEach(element -> targetList.add(convert(element)));
		return targetList;
    }
}