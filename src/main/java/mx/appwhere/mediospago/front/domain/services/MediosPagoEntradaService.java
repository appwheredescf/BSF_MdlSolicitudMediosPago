package mx.appwhere.mediospago.front.domain.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlProcesoDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;

public interface MediosPagoEntradaService {

    List<ResGralDto> validarNombresArchivos(Map<String, FileProcessor> lstFileProcess);
    
    List<ResGralDto> validarCifrasControl (Map<String, FileProcessor> mapFileProcess, Long nRegistrosAP);
    
    List<ResGralDto> validarArchivoPrincipalGenerarSalida (Map<String, FileProcessor> mapFileProcess, File tempDirectory);

    FileProcessor crearArchivoSalidaCuenta(EtlProcesoDto etlProcesoDto, File tempDirectory);

    File crearArchivoErrores(List<ResGralDto> lstErroresDto, File tempDirectory);

}
