package mx.appwhere.mediospago.front.application.dto.ejemplo;


public class EjemploDto {

    private String response;
    private String numeroCodigo;

    public EjemploDto() {
    }

    public EjemploDto(String response, String numeroCodigo) {
        this.response = response;
        this.numeroCodigo = numeroCodigo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getNumeroCodigo() {
        return numeroCodigo;
    }

    public void setNumeroCodigo(String numeroCodigo) {
        this.numeroCodigo = numeroCodigo;
    }
}
