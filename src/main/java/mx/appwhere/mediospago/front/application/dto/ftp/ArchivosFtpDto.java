package mx.appwhere.mediospago.front.application.dto.ftp;

import java.util.List;

import org.apache.commons.net.ftp.FTPFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArchivosFtpDto extends ResGralDto{

    private List<FTPFile> listaArchivosFtpProcesar;
    
}
