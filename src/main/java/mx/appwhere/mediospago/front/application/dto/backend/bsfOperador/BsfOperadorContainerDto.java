package mx.appwhere.mediospago.front.application.dto.backend.bsfOperador;

public class BsfOperadorContainerDto {

    private BsfOperadorDto BSFOPERADOR;

    public BsfOperadorContainerDto() {
    }

    public BsfOperadorContainerDto(BsfOperadorDto BSFOPERADOR) {
        this.BSFOPERADOR = BSFOPERADOR;
    }

    public BsfOperadorDto getBSFOPERADOR() {
        return BSFOPERADOR;
    }

    public void setBSFOPERADOR(BsfOperadorDto BSFOPERADOR) {
        this.BSFOPERADOR = BSFOPERADOR;
    }
}
