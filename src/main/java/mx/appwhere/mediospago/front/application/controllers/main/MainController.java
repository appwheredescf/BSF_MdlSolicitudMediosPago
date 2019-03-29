package mx.appwhere.mediospago.front.application.controllers.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.constants.ViewsLocation;

/**
 * @version 1.0 07/02/2018
 */
@RestController
@RequestMapping("/menu")
public class MainController {

    private String bsfOperador = "ig6bpA59BmxGAIwqUPcX5SRRxl3om/4vKuu6jFTBSvU5gKmGr1uJw8UtEY0a81/ln/qt7jxIHGE9hcv8i60x9rw3UcfjlUuxGNYdqApyMVQnLV0azPv5oSEj221hESltJoMudg5mrVMJuRQ67Z1PCJox4YVPif7gM7nqBFnQQJvsnPhSruXYjMfQ0Ha0Uu0M3i+CBAPcSuzCGm2RMV5R4A7JiyOn/hzH2klVf4Z0sjLDF8zkMbGiUYtajjdw9wdQ0+DzXMqZlRum17dmFaWCG1pjytF+iS7h4Z+qGaG1u4mg4CUzDtK+hghyEEY9qn4evOfrNRA33OJqu7/8PvOmdjwkiv3+u+s3D5oa10skIKOa7Eel5g9qkGKPSyGhpj361ILU+GlVy5Mds2fR+qJLIuzz6Yzunvm7YLsCBO24EN0j3YoZr0FyeivdH4Qzu3Fe2BCJCXcqLiPXVN2YxA+f9d/qRTpC8y2rqUFpZkapyqxT81tIQVR1Vhjggfh4J/n1YNYn3TkyAji3mmoqwGtyz3DVow43/LufuHpuY5gTTH/1Q7CKZbCmGLbXKLojxJQU";
    /**
     * Nombre getMainView
     * Proposito Presentar el formulario para enviar mediante POST en bsfOperador
     * Inputs -
     * @return ModelAndView hacia el archivo HTML /main/main.html
     */
    @GetMapping(produces = ApplicationConstants.VIEWS_PRODUCE_HTML)
    public ModelAndView getMainView() {
        ModelAndView mav = new ModelAndView(ViewsLocation.MAIN_VIEW);
        mav.addObject("BSFOPERADOR", bsfOperador);
        return mav;
    }
}
