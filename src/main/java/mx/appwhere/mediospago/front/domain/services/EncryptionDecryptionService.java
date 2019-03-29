package mx.appwhere.mediospago.front.domain.services;

import mx.appwhere.mediospago.front.application.dto.backend.encryptionDecryption.EncryptionDecryptionResponseDto;

public interface EncryptionDecryptionService {

    EncryptionDecryptionResponseDto encrypt(String textToEncrypt);
    EncryptionDecryptionResponseDto decrypt(String textToDecrypt);

}
