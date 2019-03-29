package mx.appwhere.mediospago.front.application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.appwhere.mediospago.front.application.constants.ServiceMessages;
import mx.appwhere.mediospago.front.application.dto.backend.bsfOperador.BsfOperadorContainerDto;
import mx.appwhere.mediospago.front.application.dto.backend.bsfOperador.BsfOperadorDto;
import mx.appwhere.mediospago.front.domain.exceptions.BsfOperadorException;
import mx.appwhere.mediospago.front.domain.services.BsfOperadorService;
import mx.appwhere.mediospago.front.domain.services.EncryptionDecryptionService;
import mx.appwhere.mediospago.front.domain.util.Util;

@Service
public class BsfOperadorServiceImpl implements BsfOperadorService {

    private EncryptionDecryptionService encryptionDecryptionService;
    private Util util;

    /**
     * Inyeccion de dependenciass
     */
    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    @Autowired
    public void setEncryptionDecryptionService(EncryptionDecryptionService encryptionDecryptionService) {
        this.encryptionDecryptionService = encryptionDecryptionService;
    }

    /**
     * Metodo para desencriptar bsfOperador
     *
     * @param bsfOperadorEncrypted {@link String}
     * @return {@link BsfOperadorDto}
     */
    @Override
    public BsfOperadorDto decryptBsfOperador(String bsfOperadorEncrypted) {
        String bsfOperadorJson = this.encryptionDecryptionService.decrypt(bsfOperadorEncrypted).getRespuesta();
        try {
            BsfOperadorContainerDto bsfOperadorContainerDto = new BsfOperadorContainerDto();
            bsfOperadorContainerDto =
                    (BsfOperadorContainerDto)
                            this.util.jsonToObject(bsfOperadorContainerDto,bsfOperadorJson);
            return bsfOperadorContainerDto.getBSFOPERADOR();
        } catch (Exception e) {
            throw new BsfOperadorException(ServiceMessages.BSFOPERADOR_ERROR);
        }
    }

    /**
     * Metodo para encriptar bsfOperador
     *
     * @param bsfOperadorDto {@link BsfOperadorDto}
     * @return {@link String}
     */
    @Override
    public String encryptBsfOperador(BsfOperadorDto bsfOperadorDto) {
        try {
            return this.encryptionDecryptionService.encrypt(this.util.objectToJson(bsfOperadorDto))
                    .getRespuesta();
        } catch (Exception ex) {
            throw new BsfOperadorException(ServiceMessages.BSFOPERADOR_ERROR);
        }
    }

}
