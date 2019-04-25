package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.exceptions.FileOperationException;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 *
 * @author JoseBarrios
 * @since 11/04/2019
 *
 */
public interface FileProcessor {

    String obtenerCampo(EtlCampoArchivoDto campoArchivoDto);

    List<ResGralDto> validarCamposArchivo();

    void open() throws FileOperationException;

    EtlArchivoDto getArchivoDto();

    void setArchivoDto(EtlArchivoDto etlArchivoDto);

    File getFile();

    void setFile(File file);

    void close();

    int getCurrentLine();

}
