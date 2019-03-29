package mx.appwhere.mediospago.front.application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mx.appwhere.mediospago.front.application.constants.ViewsLocation;
import mx.appwhere.mediospago.front.application.dto.backend.bsfOperador.BsfOperadorDto;
import mx.appwhere.mediospago.front.application.dto.backend.encryptionDecryption.EncryptionDecryptionResponseDto;
import mx.appwhere.mediospago.front.application.dto.ejemplo.EjemploDto;
import mx.appwhere.mediospago.front.application.dto.ejemplo.ExampleDto;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.FormatException;
import mx.appwhere.mediospago.front.domain.services.BsfOperadorService;
import mx.appwhere.mediospago.front.domain.services.ConverterService;
import mx.appwhere.mediospago.front.domain.services.MainService;

@Service
public class MainServiceImpl implements MainService {

    @Value("${jboss.digitalizacion.uri}")
    private String uriDigitalizacion;
    private BsfOperadorService bsfOperadorService;
    private String digitalizacion = "ig6bpA59BmxGAIwqUPcX5SRRxl3om/4vKuu6jFTBSvU5gKmGr1uJw8UtEY0a81/ln/qt7jxIHGE9hcv8i60x9l3wMLwI0JzXABXqlKpSRnAjEZyjRb3Gw9pX9RD79azdVLMxK77tK+V9DMxQrXQyXzcdZYZfFAZdAv46EBax9n2RQeDMm/YyZ4nWfPcM4mkXP3Nbrl7+W7AyLIxZJsfjPAcsjDwPk6LODqB+2Igq+qJUWQd1Q+8DOdq3ITGxkyIHaiptUThyiP5LsDt0Lk2LhJViS+gguFhWGwfSvg3gtH0lbNDqHnWd/ryTAt0fXzbYNiJ82hA5nxKCVG4VEfcGefu9qcFxcqRd4pOs13p6sJN8scY3gPAp8clP09GDI7KzQQEpCP5laRlQUFz92e7K2j9HhbrYQexRzj8ZEZfOf7fWGHEsIO84ax6tdVT4fH7vLt9aoHWk5vluxKjJlLOLlHqyIeUZRuQrxWRqN4J/6WN/Bi+68coYJfZs5egDiXK0y4ORPULj7zIKAjCOCTnfLnbShp46qNW5+E+y9HD3ulPzOEPKqkTBdnRXEH9acF82rlIG90i/R787bCK93gyzKoD4WQh6UpU6GEM9Kp02zCaYJC/OA3eEujRMI9l3fSMGV0zKidxo9+iA2ozkPPR4MNi7PYupdsghHVchsvU5VrUbJGKocQ2oIZDsihsyJn+mlhHLN8tmVCwmsa9Pq2uL6y0i1c+6V7fFiHrrG+TYQXI=";
    private ConverterService converterService;

    @Autowired
    public void setConverterService(ConverterService converterService) {
        this.converterService = converterService;
    }

    @Autowired
    public void setBsfOperadorService(BsfOperadorService bsfOperadorService) {
        this.bsfOperadorService = bsfOperadorService;
    }

    @Override
    public ModelAndView getMain(String bsfOperador) {
        ModelAndView mav = new ModelAndView(ViewsLocation.EXAMPLE_VIEW);
        BsfOperadorDto bsfOperadorDto =
                bsfOperadorService.decryptBsfOperador(bsfOperador);
        mav.addObject("nombre", bsfOperadorDto.getNOMBREEMPLEADO());
        mav.addObject("transportUri", uriDigitalizacion);
        mav.addObject("transport", digitalizacion);
        return mav;
    }

    @Override
    public ModelAndView getMainTranport(String transport) {
        ModelAndView mav = new ModelAndView(ViewsLocation.EXAMPLE_VIEW);
        BsfOperadorDto bsfOperadorDto =
                bsfOperadorService.decryptBsfOperador(transport);
        mav.addObject("nombre", bsfOperadorDto.getNOMBREEMPLEADO());
        mav.addObject("transportUri", uriDigitalizacion);
        mav.addObject("transport", digitalizacion);
        mav.addObject("doc", bsfOperadorDto.getTRANSPORT().getIDDOCUMENTO());
        return mav;
    }

    @Override
    public EjemploDto utilizarConverter() {
        EncryptionDecryptionResponseDto encryptionDecryptionResponseDto =
                new EncryptionDecryptionResponseDto("123", "", "Appwhere");
        return converterService.convert(encryptionDecryptionResponseDto, EjemploDto.class);
    }

    @Override
    public EjemploDto getValidation(ExampleDto exampleDto) throws FormatException {
        if (exampleDto.getAge() == 30)
            throw new FormatException("AgeError", "Este es un ejemplo con FormatException al ser la edad igual a 30");
        EjemploDto ejemploDto = new EjemploDto();
        ejemploDto.setNumeroCodigo("12");
        ejemploDto.setResponse("Datos Guardados");
        return ejemploDto;
    }
}
