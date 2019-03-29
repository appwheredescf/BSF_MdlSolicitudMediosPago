package mx.appwhere.mediospago.front.application.dto.backend.encryptionDecryption;

public class EncryptionDecryptionRequestDto {

    private String text;

    public EncryptionDecryptionRequestDto() {
    }

    public EncryptionDecryptionRequestDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
