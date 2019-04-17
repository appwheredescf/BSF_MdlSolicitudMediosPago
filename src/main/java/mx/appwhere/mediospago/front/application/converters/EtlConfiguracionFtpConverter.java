package mx.appwhere.mediospago.front.application.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.experimental.UtilityClass;
import mx.appwhere.mediospago.front.application.dto.etl.EtlConfiguracionFtpDto;
import mx.appwhere.mediospago.front.domain.entities.EtlConfiguracionFtpEntity;
import mx.appwhere.mediospago.front.domain.entities.EtlServidorFtpEntity;

/**
 * Class to convert EtlConfiguracionFtp to Dto Object
 * 
 * @author JoseBarrios
 *
 */
@UtilityClass
public class EtlConfiguracionFtpConverter {
    
    
    public static EtlConfiguracionFtpDto convert(EtlConfiguracionFtpEntity source) {
	
		EtlConfiguracionFtpDto target = new EtlConfiguracionFtpDto();

		target.setId(source.getId());
		target.setDirectorio(source.getDirectorio());
		target.setCveConfiguracion(source.getCveConfiguracion());
		target.setPrefijoDirectorio(source.getPrefijoDirectorio());

		if (Objects.nonNull(source.getServidorFtpEntity())) {
			EtlServidorFtpEntity servidorFtpEntiry = source.getServidorFtpEntity();
			target.setDireccionIp(servidorFtpEntiry.getDireccionIp());
			target.setUsuario(servidorFtpEntiry.getUsuario());
			target.setPassword(servidorFtpEntiry.getPassword());
			target.setPuerto(servidorFtpEntiry.getPuerto());
			target.setNombreServidor(servidorFtpEntiry.getNombre());
		}

		return target;
    }
    
    public static List<EtlConfiguracionFtpDto> convert(List<EtlConfiguracionFtpEntity> sourceList) {
		List<EtlConfiguracionFtpDto> targetList = new ArrayList<>();
		sourceList.forEach(configuradionFtpEntity -> targetList.add(convert(configuradionFtpEntity)));
		return targetList;
    }
}