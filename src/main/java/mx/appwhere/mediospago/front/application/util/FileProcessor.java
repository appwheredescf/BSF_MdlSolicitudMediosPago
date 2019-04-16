package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author JoseBarrios
 * @since 11/04/2019
 *
 */
public interface FileProcessor {

    void open() throws IOException;

    EtlArchivoDto getArchivoDto();

    File getFile();

    void close();

}
