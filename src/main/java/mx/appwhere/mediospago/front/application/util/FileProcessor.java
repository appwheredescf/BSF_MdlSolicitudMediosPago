package mx.appwhere.mediospago.front.application.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * Class responsible for handling and manipulating text files
 * 
 * @author JoseBarrios
 *
 */
public class FileProcessor {

    private LineIterator lineIterator;
    
    public FileProcessor(String pathname) throws IOException {
	lineIterator = FileUtils.lineIterator(new File(pathname));
    }
    
    public FileProcessor(File file) throws IOException {
	lineIterator = FileUtils.lineIterator(file);
    }
    
    public String getNextLine () {
	String line = null;
	if (lineIterator.hasNext()) {
	    line = lineIterator.nextLine();
	}
	return line;
    }
    
    public void close() {
	LineIterator.closeQuietly(lineIterator);
    }
}
