package mx.appwhere.mediospago.front.domain.services;

import org.springframework.web.servlet.ModelAndView;

import mx.appwhere.mediospago.front.application.dto.ejemplo.EjemploDto;
import mx.appwhere.mediospago.front.application.dto.ejemplo.ExampleDto;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.FormatException;

public interface MainService {
    ModelAndView getMain(String BsfOperador);

    ModelAndView getMainTranport(String transport);

    EjemploDto utilizarConverter();

    EjemploDto getValidation(ExampleDto exampleDto)throws FormatException;
}
