package mx.appwhere.mediospago.front.application.dto.backend.bsfOperador;

public class BsfOperadorDto {

    private String ENTIDAD;
    private String CENTRO;
    private Integer TERMINAL;
    private String USERTCB;
    private String SESSIONID;
    private Object PASSTCB;
    private String NOMBREEMPLEADO;
    private String NOMBRECENTRO;
    private String FECHACONTABLE;
    private DatosCentroDto DIRECCIONCENTRO;
    private TransportDto TRANSPORT;

    public BsfOperadorDto() {
    }

    public BsfOperadorDto(String ENTIDAD, String CENTRO, Integer TERMINAL, String USERTCB, String SESSIONID, Object PASSTCB, String NOMBREEMPLEADO, String NOMBRECENTRO, String FECHACONTABLE, DatosCentroDto DIRECCIONCENTRO, TransportDto TRANSPORT) {
        this.ENTIDAD = ENTIDAD;
        this.CENTRO = CENTRO;
        this.TERMINAL = TERMINAL;
        this.USERTCB = USERTCB;
        this.SESSIONID = SESSIONID;
        this.PASSTCB = PASSTCB;
        this.NOMBREEMPLEADO = NOMBREEMPLEADO;
        this.NOMBRECENTRO = NOMBRECENTRO;
        this.FECHACONTABLE = FECHACONTABLE;
        this.DIRECCIONCENTRO = DIRECCIONCENTRO;
        this.TRANSPORT = TRANSPORT;
    }

    public String getENTIDAD() {
        return ENTIDAD;
    }

    public void setENTIDAD(String ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }

    public String getCENTRO() {
        return CENTRO;
    }

    public void setCENTRO(String CENTRO) {
        this.CENTRO = CENTRO;
    }

    public Integer getTERMINAL() {
        return TERMINAL;
    }

    public void setTERMINAL(Integer TERMINAL) {
        this.TERMINAL = TERMINAL;
    }

    public String getUSERTCB() {
        return USERTCB;
    }

    public void setUSERTCB(String USERTCB) {
        this.USERTCB = USERTCB;
    }

    public String getSESSIONID() {
        return SESSIONID;
    }

    public void setSESSIONID(String SESSIONID) {
        this.SESSIONID = SESSIONID;
    }

    public Object getPASSTCB() {
        return PASSTCB;
    }

    public void setPASSTCB(Object PASSTCB) {
        this.PASSTCB = PASSTCB;
    }

    public String getNOMBREEMPLEADO() {
        return NOMBREEMPLEADO;
    }

    public void setNOMBREEMPLEADO(String NOMBREEMPLEADO) {
        this.NOMBREEMPLEADO = NOMBREEMPLEADO;
    }

    public String getNOMBRECENTRO() {
        return NOMBRECENTRO;
    }

    public void setNOMBRECENTRO(String NOMBRECENTRO) {
        this.NOMBRECENTRO = NOMBRECENTRO;
    }

    public String getFECHACONTABLE() {
        return FECHACONTABLE;
    }

    public void setFECHACONTABLE(String FECHACONTABLE) {
        this.FECHACONTABLE = FECHACONTABLE;
    }

    public DatosCentroDto getDIRECCIONCENTRO() {
        return DIRECCIONCENTRO;
    }

    public void setDIRECCIONCENTRO(DatosCentroDto DIRECCIONCENTRO) {
        this.DIRECCIONCENTRO = DIRECCIONCENTRO;
    }

    public TransportDto getTRANSPORT() {
        return TRANSPORT;
    }

    public void setTRANSPORT(TransportDto TRANSPORT) {
        this.TRANSPORT = TRANSPORT;
    }
}
