package mx.appwhere.mediospago.front.application.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;

/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class FileProcessor {

    private LineIterator lineIterator;
    
    private EtlArchivoDto archivoDto;
    
    private File file;
    
    private int currentLine;
    
    public FileProcessor(String pathname, EtlArchivoDto archivoDto) {
	this.archivoDto = archivoDto;
	file = new File(pathname);
    }
    
    public FileProcessor(File file, EtlArchivoDto archivoDto) {
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
		if (file.getName().endsWith(etlArchivo.getExtension())) {
		    mapFileProcessor.put(etlArchivo.getExtension(), new FileProcessor(file, etlArchivo));  
                }
	    }
	});
	
	return mapFileProcessor;
    }
    
    public Long countLines() throws IOException {
	FileReader fr = new FileReader(file);
	BufferedReader bufferedReader = new BufferedReader(fr);
	Long lines = bufferedReader.lines().mapToLong(dssd -> 1).count();
	bufferedReader.close();
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