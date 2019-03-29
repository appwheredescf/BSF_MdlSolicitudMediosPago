package mx.appwhere.mediospago.front.application.converters;

import org.springframework.stereotype.Component;

import mx.appwhere.mediospago.front.application.dto.backend.encryptionDecryption.EncryptionDecryptionResponseDto;
import mx.appwhere.mediospago.front.application.dto.ejemplo.EjemploDto;

/**
 * @author Jose Angel Hernandez
 * @version 1.0 - 2018/02/20
 */
@Component
public class EncryptionDecryptResponseDtoToEjemploDto extends AbstractConverter<EncryptionDecryptionResponseDto, EjemploDto> {
    @Override
    public EjemploDto convert(EncryptionDecryptionResponseDto source) {
        EjemploDto ejemploDto= new EjemploDto();
        ejemploDto.setNumeroCodigo(source.getCodRet());
        ejemploDto.setResponse(source.getRespuesta());
        return ejemploDto;

    }
}
