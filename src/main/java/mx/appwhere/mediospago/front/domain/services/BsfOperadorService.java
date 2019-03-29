package mx.appwhere.mediospago.front.domain.services;

import mx.appwhere.mediospago.front.application.dto.backend.bsfOperador.BsfOperadorDto;

public interface BsfOperadorService {

    BsfOperadorDto decryptBsfOperador(String bsfOperadorEncrypted);

    String encryptBsfOperador(BsfOperadorDto bsfOperadorDto);

}
