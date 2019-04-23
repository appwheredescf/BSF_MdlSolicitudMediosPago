package mx.appwhere.mediospago.front.application.controllers;

import mx.appwhere.mediospago.front.application.scheduled.MediosPagoEntradaScheduled;
import mx.appwhere.mediospago.front.application.scheduled.MediosPagoSalidaScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JoseBarrios
 * @since 17/04/2019
 *
 */

@RestController
@RequestMapping("scheduled")
public class ScheduledController {

    private MediosPagoEntradaScheduled mediosPagoEntradaScheduled;

    private MediosPagoSalidaScheduled mediosPagoSalidaScheduled;

    @Autowired
    public ScheduledController(MediosPagoEntradaScheduled mediosPagoEntradaScheduled,
                               MediosPagoSalidaScheduled mediosPagoSalidaScheduled) {
        this.mediosPagoEntradaScheduled = mediosPagoEntradaScheduled;
        this.mediosPagoSalidaScheduled = mediosPagoSalidaScheduled;
    }

    @GetMapping(value = "/ejecutar/mediosPagoEntrada")
    public void ejecutarMediosPagoEntrada() {
        mediosPagoEntradaScheduled.ejecutarProceso();
    }

    @GetMapping(value = "/ejecutar/mediosPagoSalida")
    public void ejecutarMediosPagoSalida() {
        mediosPagoSalidaScheduled.ejecutarProceso();
    }
}