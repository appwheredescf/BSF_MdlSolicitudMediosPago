package mx.appwhere.mediospago.front.application.controllers.example;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.controllers.ExceptionController;
import mx.appwhere.mediospago.front.application.dto.ejemplo.EjemploDto;
import mx.appwhere.mediospago.front.application.dto.ejemplo.ExampleDto;
import mx.appwhere.mediospago.front.domain.entities.EtlArchivoEntity;
import mx.appwhere.mediospago.front.domain.entities.EtlProcesoEntity;
import mx.appwhere.mediospago.front.domain.exceptions.ViewException;
import mx.appwhere.mediospago.front.domain.exceptions.ajax.FormatException;
import mx.appwhere.mediospago.front.domain.repositories.EtlProcesoRepository;
import mx.appwhere.mediospago.front.domain.services.MainService;

@RestController
@RequestMapping("example")
public class ExampleController extends ExceptionController {

    private MainService mainService;
    
    private EtlProcesoRepository etlProcesoRepository;
    

    @Autowired
    public ExampleController(MainService mainService, EtlProcesoRepository etlProcesoRepository) {
        this.mainService = mainService;
        this.etlProcesoRepository = etlProcesoRepository;
    }

    @PostMapping(produces = ApplicationConstants.VIEWS_PRODUCE_HTML, params = "BSFOPERADOR")
    public ModelAndView getView(@RequestParam("BSFOPERADOR") String bsfOperadorEncrypt) {
	
	Optional<EtlProcesoEntity> etlProcesoOptional = etlProcesoRepository.findByCveProceso("MED");
	if (etlProcesoOptional.isPresent()) {
	    EtlProcesoEntity result = etlProcesoOptional.get();
	    
	    List<EtlArchivoEntity> listaArchivos = result.getArchivosProceso();
	    EtlArchivoEntity entyty = listaArchivos.get(0);

	}
	
        return mainService.getMain(bsfOperadorEncrypt);
    }

    @PostMapping(params = "TRANSPORT", produces = ApplicationConstants.VIEWS_PRODUCE_HTML)
    public ModelAndView getView2(@RequestParam("TRANSPORT") String transportEncrypt) {
        return mainService.getMainTranport(transportEncrypt);
    }

    @GetMapping(value = "converter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EjemploDto utilizarConverter() {
        return mainService.utilizarConverter();
    }

    @GetMapping(value = "get500", produces = ApplicationConstants.VIEWS_PRODUCE_HTML)
    public String get500() {
        throw new ViewException("hola nuevo error 500");
    }

    @PostMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
    public EjemploDto getValidation(@Valid ExampleDto exampleDto) throws FormatException {
        return mainService.getValidation(exampleDto);
    }
}
