package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.exceptions.FileOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Collections;
import java.util.List;


/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class OutFileProcessor implements FileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutFileProcessor.class);

    private EtlArchivoDto archivoDto;

    private OutputStream outStream;

    private File file;

    public void write(String line) throws FileOperationException {

        try {
            outStream.write(line.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new FileOperationException(ApplicationConstants.ETL_ERR_ESCTIRURA_ARCHIVO, file.getName());
        }
    }

    @Override
    public void open() throws FileOperationException {

        try {
            this.outStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new FileOperationException(ApplicationConstants.ETL_ERR_ESCTIRURA_ARCHIVO, file.getName());
        }
    }

    @Override
    public void close() {
        try {
            if (outStream != null) {
                outStream.close();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public int getCurrentLine() {
        return 0;
    }

    public String obtenerCampo(EtlCampoArchivoDto campoArchivoDto) {
        return "";
    }

    public List<ResGralDto> validarCamposArchivo(){
        return Collections.emptyList();
    }


    @Override
    public EtlArchivoDto getArchivoDto() {
        return archivoDto;
    }

    public void setArchivoDto(EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
    }

    @Override
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}