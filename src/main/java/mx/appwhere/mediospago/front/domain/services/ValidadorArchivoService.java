package mx.appwhere.mediospago.front.domain.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;

public interface ValidadorArchivoService {

    List<ResGralDto> validarNombresArchivos(Map<String, FileProcessor> lstFileProcess, String cveProceso);
    
    List<ResGralDto> validarCifrasControl (Map<String, FileProcessor> mapFileProcess, Long nRegistrosAP);
    
    List<ResGralDto> validarArchivoPrincipalGenerarSalida (Map<String, FileProcessor> mapFileProcess, File tempDirectory);
    
}
