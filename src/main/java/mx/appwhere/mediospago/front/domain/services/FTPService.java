package mx.appwhere.mediospago.front.domain.services;

import java.io.File;

import mx.appwhere.mediospago.front.application.dto.etl.EtlConfiguracionFtpDto;
import mx.appwhere.mediospago.front.application.dto.ftp.ArchivosFtpDto;

public interface FTPService {
    
    ArchivosFtpDto listDirectories(EtlConfiguracionFtpDto configFtpDto);
    
    File ftpToLocalDirectory(String directoryLocal, EtlConfiguracionFtpDto configFtpDto, String directoryFtp);
}
