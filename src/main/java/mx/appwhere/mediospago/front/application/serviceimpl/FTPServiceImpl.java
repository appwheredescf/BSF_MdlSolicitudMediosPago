package mx.appwhere.mediospago.front.application.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.util.FileProcessor;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.etl.EtlConfiguracionFtpDto;
import mx.appwhere.mediospago.front.application.dto.ftp.ArchivosFtpDto;
import mx.appwhere.mediospago.front.application.scheduled.MediosPagoEntradaScheduled;
import mx.appwhere.mediospago.front.domain.services.FTPService;

@Service
public class FTPServiceImpl implements FTPService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MediosPagoEntradaScheduled.class);

    private ApplicationContext applicationContext;

    @Autowired
    public FTPServiceImpl (ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
    
    private boolean connect(FTPClient ftpClient, EtlConfiguracionFtpDto configFtpDto) throws IOException {
	
	ftpClient.connect(configFtpDto.getDireccionIp(), configFtpDto.getPuerto());
		return ftpClient.login(configFtpDto.getUsuario(), configFtpDto.getPassword());
    }
    
    public ArchivosFtpDto listDirectories(EtlConfiguracionFtpDto configFtpDto) {
	
		ArchivosFtpDto archivosFtpDto = new ArchivosFtpDto();
		archivosFtpDto.setEstatus(ApplicationConstants.ERR);

		FTPClient ftpClient = new FTPClient();
		try {
			if (connect(ftpClient, configFtpDto)) {

				ftpClient.changeWorkingDirectory(configFtpDto.getDirectorio());

				List<FTPFile> listaArchivosFtp = new ArrayList<>(Arrays.asList(ftpClient.mlistDir()));

				List<FTPFile> listaArchivosFtpProcesar = listaArchivosFtp.stream()
					.filter(archivoFtp -> archivoFtp.isDirectory()
						&& archivoFtp.getName().startsWith(configFtpDto.getPrefijoDirectorio()))
					.collect(Collectors.toList());

				archivosFtpDto.setListaArchivosFtpProcesar(listaArchivosFtpProcesar);
				archivosFtpDto.setEstatus(ApplicationConstants.OK);

				ftpClient.logout();
				ftpClient.disconnect();
			} else {
				archivosFtpDto.setMensaje("Error en credenciales de Servidor Ftp: " + configFtpDto.getNombreServidor());
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			archivosFtpDto.setEstatus(ApplicationConstants.ERR);
		}
	
		return archivosFtpDto;
    }
    
    public File ftpToLocalDirectory(String directoryLocal, EtlConfiguracionFtpDto configFtpDto, String directoryFtp) {

		FTPClient ftpClient = new FTPClient();

		/** Creamos el directorio en el server */
		String pathNewDirectoryLocal = directoryLocal + File.separator + directoryFtp;
		File fileLocal = new File(pathNewDirectoryLocal);
		if (!fileLocal.exists()) {
			fileLocal.mkdir();
		}

		try {
			if (connect(ftpClient, configFtpDto)) {

				ftpClient.changeWorkingDirectory(configFtpDto.getDirectorio() + File.separator + directoryFtp);

				FTPFile[] ftpFiles = ftpClient.listFiles();
				for (FTPFile ftpFile : ftpFiles) {

					OutputStream output = new FileOutputStream(pathNewDirectoryLocal + File.separator + ftpFile.getName());
					ftpClient.retrieveFile(ftpFile.getName(), output);
					output.close();
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return fileLocal;
    }

    @Override
	public Map<String, FileProcessor> obtenerArchivosProceso(File directory, List<EtlArchivoDto> archivosProceso) {

    	Map<String, FileProcessor> mapFileProcessor = new HashMap<>();

		archivosProceso.forEach(etlArchivo -> {

			for(File file : directory.listFiles()) {
				if (file.getName().endsWith(etlArchivo.getExtension())
						&& etlArchivo.getTipoArchivo().shortValue() == ApplicationConstants.CAT_TIPO_ARCHIVO_ENTRADA) {

					FileProcessor fileProcessor;

					if (etlArchivo.getExtension().equals(ApplicationConstants.TIPO_ARCHIVO_TARJETAS)) {
						fileProcessor = (FileProcessor)applicationContext.getBean("inExcelFileProcessor");
					} else {
						fileProcessor = (FileProcessor)applicationContext.getBean("inFileProcessor");
					}

					fileProcessor.setArchivoDto(etlArchivo);
					fileProcessor.setFile(file);
					mapFileProcessor.put(etlArchivo.getExtension(), fileProcessor);
				}
			}
		});

		return mapFileProcessor;
	}
}
