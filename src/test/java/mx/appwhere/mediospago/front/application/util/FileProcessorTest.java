package mx.appwhere.mediospago.front.application.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;

@RunWith(SpringRunner.class)
public class FileProcessorTest {
    
    
    private File loadFile() {
	return new File("C:\\MEDIOS_PAGO\\MED_SOL_CTA_TAR_TC19209_MB02_04.A_P"); 
    }
    
    
    @Test
    public void readLine() throws IOException { 
	File file =  loadFile(); 
	if (file.exists()) {
	    FileProcessor fileProcessor = new FileProcessor(file, new EtlArchivoDto());
	    
	    assertThat(fileProcessor.getNextLine()).isNotBlank();
	    fileProcessor.close();
	}
    }
    
    @Test
    public void closeFile() throws IOException { 
	File file =  loadFile(); 
	if (file.exists()) {
	    FileProcessor fileProcessor = new FileProcessor(file, new EtlArchivoDto());
	    fileProcessor.close();
	    
	    assertThat(fileProcessor.getNextLine()).isNull();
	}
    }
}
