package mx.appwhere.mediospago.front.application.converters;

import lombok.experimental.UtilityClass;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCatTipoRellenoDto;
import mx.appwhere.mediospago.front.domain.entities.EtlCamposArchivoEntity;
import mx.appwhere.mediospago.front.domain.entities.EtlCatTipoRellenoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to convert EtlCatTipoRellenoEntity to Dto Object
 *
 * @author JoseBarrios
 *
 */
@UtilityClass
public class EtlCatTipoRellenoConverter {

    public static EtlCatTipoRellenoDto convert(EtlCatTipoRellenoEntity source) {
        EtlCatTipoRellenoDto target = new EtlCatTipoRellenoDto();
        target.setCaracter(source.getCaracter());
        target.setDescripcion(source.getDescripcion());
        target.setIzquierda(source.getIzquierda());
        return target;
    }

    public static List<EtlCatTipoRellenoDto> convert(List<EtlCatTipoRellenoEntity> listSource) {
        List<EtlCatTipoRellenoDto> listTarget = new ArrayList<>();
        listSource.forEach(element -> listTarget.add(convert(element)));
        return listTarget;
    }

}
