package mx.appwhere.mediospago.front.application.util;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.exceptions.FileOperationException;
import mx.appwhere.mediospago.front.domain.util.Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class InFileProcessor implements FileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InFileProcessor.class);

    @Autowired
    private Util util;

    private LineIterator lineIterator;
    
    private EtlArchivoDto archivoDto;

    private File file;
    
    private int currentLine;

    private String currentLineStr;

    public void open() throws FileOperationException {
        try {
            currentLine = 0;
            lineIterator = FileUtils.lineIterator(file);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new FileOperationException(ApplicationConstants.ETL_ERR_LECTURA_ARCHIVO, file.getName());
        }
    }
    
    public boolean hasNext() {
	    return lineIterator.hasNext();
    }
    
    public String getNextLine () {
        currentLine++;
        currentLineStr = lineIterator.nextLine();
        return currentLineStr;
    }

    public List<ResGralDto> validarCamposArchivo() {

        List<ResGralDto> lstErrores = new ArrayList<>();

        if (archivoDto.getLongitudLinea() == currentLineStr.length()) {

            if(!util.hasSpecialCharacters(currentLineStr)) {

                if (!util.hasLowerCase(currentLineStr)) {

                    archivoDto.getListaCampos().forEach(campoArchivoDto -> {

                        String campo = obtenerCampo(campoArchivoDto);

                        ResGralDto resGralDto = util.validarCampoArchivo(campoArchivoDto, campo, currentLine, archivoDto.getExtension());

                        if (resGralDto.getEstatus() == ApplicationConstants.ERR) {
                            lstErrores.add(resGralDto);
                        }
                    });
                } else {
                    lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_MINUSCULAS, currentLine, archivoDto.getExtension()));
                }
            } else {
                lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_CARACTERES_ESP, currentLine, archivoDto.getExtension()));
            }
        } else {
            lstErrores.add(util.crearErrorDto(ApplicationConstants.ETL_ERR_LONGITUD_LINEA, currentLine, archivoDto.getExtension()));
        }

        return lstErrores;
    }
    
    public void close() {
	    LineIterator.closeQuietly(lineIterator);
    }
    
    public Long countLines() {
        Long lines = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            lines = bufferedReader.lines().mapToLong(dssd -> 1).count();
            bufferedReader.close();
        } catch (IOException e) { }
        return lines;
    }

    public String obtenerCampo(EtlCampoArchivoDto campoArchivoDto) {
        return currentLineStr.substring(campoArchivoDto.getPosicionInicial() - 1, campoArchivoDto.getPosicionFinal());
    }


    @Override
    public EtlArchivoDto getArchivoDto() {
        return archivoDto;
    }

    @Override
    public void setArchivoDto(EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }
}