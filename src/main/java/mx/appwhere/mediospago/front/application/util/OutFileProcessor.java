package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class OutFileProcessor implements FileProcessor {

    private EtlArchivoDto archivoDto;

    private OutputStream outStream;

    private File file;

    public OutFileProcessor(String pathname, EtlArchivoDto archivoDto) throws FileNotFoundException{
        this.archivoDto = archivoDto;
        this.file = new File(pathname);
    }

    public OutFileProcessor(File file, EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public EtlArchivoDto getArchivoDto() {
        return archivoDto;
    }

    public void write(String line) throws IOException {
        outStream.write(line.getBytes());
    }

    @Override
    public void open() throws FileNotFoundException {
        this.outStream = new FileOutputStream(file);
    }

    @Override
    public void close() {
        try {
            outStream.close();
        } catch (IOException e) {}
    }

}