package mx.appwhere.mediospago.front.application.util;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;

/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class InFileProcessor implements FileProcessor {

    private LineIterator lineIterator;
    
    private EtlArchivoDto archivoDto;
    
    private File file;
    
    private int currentLine;
    
    public InFileProcessor(String pathname, EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
        this.file = new File(pathname);
    }
    
    public InFileProcessor(File file, EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
        this.file = file;
    }
    
    public void open() throws IOException {
        currentLine = 0;
        lineIterator = FileUtils.lineIterator(file);
    }
    
    public boolean hasNext() {
	    return lineIterator.hasNext();
    }
    
    public String getNextLine () {
        currentLine++;
        return lineIterator.nextLine();
    }
    
    public void close() {
	    LineIterator.closeQuietly(lineIterator);
    }
    
    public static Map<String, FileProcessor> getFilesProcess(File directory, List<EtlArchivoDto> archivosProceso) {

        Map<String, FileProcessor> mapFileProcessor = new HashMap<>();

        archivosProceso.forEach(etlArchivo -> {
            for(File file : directory.listFiles()) {
                if (file.getName().endsWith(etlArchivo.getExtension())
                        && etlArchivo.getTipoArchivo().shortValue() == ApplicationConstants.CAT_TIPO_ARCHIVO_ENTRADA) {
                    if (etlArchivo.getExtension().equals(ApplicationConstants.TIPO_ARCHIVO_TARJETAS)) {
                        mapFileProcessor.put(etlArchivo.getExtension(), new InExcelFileProcessor(file, etlArchivo));
                    } else {
                        mapFileProcessor.put(etlArchivo.getExtension(), new InFileProcessor(file, etlArchivo));
                    }
                }
            }
        });

        return mapFileProcessor;
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

    public File getFile() {
        return file;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public EtlArchivoDto getArchivoDto() {
        return archivoDto;
    }
}