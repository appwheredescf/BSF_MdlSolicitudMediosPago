package mx.appwhere.mediospago.front.application.controllers;

import mx.appwhere.mediospago.front.application.scheduled.MediosPagoEntradaScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;

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

    @Autowired
    public ScheduledController(MediosPagoEntradaScheduled mediosPagoEntradaScheduled) {
        this.mediosPagoEntradaScheduled = mediosPagoEntradaScheduled;
    }

    @GetMapping(value = "/ejecutar/mediosPagoEntrada")
    public void ejecutarMediosPagoEntrada() {
        mediosPagoEntradaScheduled.ejecutarProceso();
    }
}
