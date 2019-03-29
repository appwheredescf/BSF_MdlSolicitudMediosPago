package mx.appwhere.mediospago.front.application.dto.backend.encryptionDecryption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Jose Angel Hernandez
 * @version 1.0 - 2018/02/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptionDecryptionResponseDto {

    @JsonProperty("codRet")
    private String codRet;

    @JsonProperty("error")
    private String error;

    @JsonProperty("respuesta")
    private String respuesta;

    public EncryptionDecryptionResponseDto() {
    }

    public EncryptionDecryptionResponseDto(String codRet, String error, String respuesta) {
        this.codRet = codRet;
        this.error = error;
        this.respuesta = respuesta;
    }

    public String getCodRet() {
        return codRet;
    }

    public void setCodRet(String codRet) {
        this.codRet = codRet;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
