package mx.appwhere.mediospago.front.domain.services;

import java.io.File;
import java.util.List;
import java.util.Map;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlConfiguracionFtpDto;
import mx.appwhere.mediospago.front.application.dto.ftp.ArchivosFtpDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;

public interface FTPService {
    
    ArchivosFtpDto listDirectories(EtlConfiguracionFtpDto configFtpDto);
    
    File ftpToLocalDirectory(String directoryLocal, EtlConfiguracionFtpDto configFtpDto, String directoryFtp);

    Map<String, FileProcessor> obtenerArchivosProceso(File directory, List<EtlArchivoDto> archivosProceso);
}
