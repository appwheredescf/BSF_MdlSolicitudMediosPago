package mx.appwhere.mediospago.front.application.dto.backend.bsfOperador;

public class DatosCentroDto {

    private String NOMBRE;
    private String FECHA_ALTA;
    private String CALLE;
    private String NUMEROEXT;
    private String NUMEROINT;
    private String COLONIA;
    private String CP;

    public DatosCentroDto() {
    }

    public DatosCentroDto(String NOMBRE, String FECHA_ALTA, String CALLE, String NUMEROEXT, String NUMEROINT, String COLONIA, String CP) {
        this.NOMBRE = NOMBRE;
        this.FECHA_ALTA = FECHA_ALTA;
        this.CALLE = CALLE;
        this.NUMEROEXT = NUMEROEXT;
        this.NUMEROINT = NUMEROINT;
        this.COLONIA = COLONIA;
        this.CP = CP;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getFECHA_ALTA() {
        return FECHA_ALTA;
    }

    public void setFECHA_ALTA(String FECHA_ALTA) {
        this.FECHA_ALTA = FECHA_ALTA;
    }

    public String getCALLE() {
        return CALLE;
    }

    public void setCALLE(String CALLE) {
        this.CALLE = CALLE;
    }

    public String getNUMEROEXT() {
        return NUMEROEXT;
    }

    public void setNUMEROEXT(String NUMEROEXT) {
        this.NUMEROEXT = NUMEROEXT;
    }

    public String getNUMEROINT() {
        return NUMEROINT;
    }

    public void setNUMEROINT(String NUMEROINT) {
        this.NUMEROINT = NUMEROINT;
    }

    public String getCOLONIA() {
        return COLONIA;
    }

    public void setCOLONIA(String COLONIA) {
        this.COLONIA = COLONIA;
    }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }
}
