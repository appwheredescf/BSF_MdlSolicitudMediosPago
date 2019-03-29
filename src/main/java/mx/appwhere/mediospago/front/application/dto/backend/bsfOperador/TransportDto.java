package mx.appwhere.mediospago.front.application.dto.backend.bsfOperador;

public class TransportDto {

    private String IDDOCUMENTO;
    private String ACTIONBACK;
    private String TITULO;
    private String TIPODOCUMENTO;
    private String TARGET;
    private String IDINTERNOPE;
    private String MESSAGE;
    private String STATUS;

    public String getIDINTERNOPE() {
        return IDINTERNOPE;
    }

    public void setIDINTERNOPE(String IDINTERNOPE) {
        this.IDINTERNOPE = IDINTERNOPE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getACTIONBACK() {
        return ACTIONBACK;
    }

    public void setACTIONBACK(String ACTIONBACK) {
        this.ACTIONBACK = ACTIONBACK;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getTIPODOCUMENTO() {
        return TIPODOCUMENTO;
    }

    public void setTIPODOCUMENTO(String TIPODOCUMENTO) {
        this.TIPODOCUMENTO = TIPODOCUMENTO;
    }

    public String getTARGET() {
        return TARGET;
    }

    public void setTARGET(String TARGET) {
        this.TARGET = TARGET;
    }

    public String getIDDOCUMENTO() {
        return IDDOCUMENTO;
    }

    public void setIDDOCUMENTO(String IDDOCUMENTO) {
        this.IDDOCUMENTO = IDDOCUMENTO;
    }
}
