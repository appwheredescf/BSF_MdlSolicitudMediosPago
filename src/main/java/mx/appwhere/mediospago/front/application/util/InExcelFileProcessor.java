package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;
import mx.appwhere.mediospago.front.application.dto.ResGralDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import mx.appwhere.mediospago.front.application.dto.etl.EtlCampoArchivoDto;
import mx.appwhere.mediospago.front.domain.exceptions.FileOperationException;
import mx.appwhere.mediospago.front.domain.util.Util;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JoseBarrios
 * @since 11/04/2019
 *
 */
public class InExcelFileProcessor implements FileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InExcelFileProcessor.class);

    @Autowired
    private Util util;

    private InputStream inputStream;

    private Workbook workbook;

    private EtlArchivoDto archivoDto;

    private File file;


    private int currentLine;

    @Override
    public void open() throws FileOperationException{
        if (workbook == null) {
            try {
                inputStream = new FileInputStream(file);
                workbook = WorkbookFactory.create(inputStream);
            } catch (IOException | InvalidFormatException | IllegalArgumentException e) {
                LOGGER.error(e.getMessage(), e);
                throw new FileOperationException(ApplicationConstants.ETL_ERR_LECTURA_ARCHIVO, file.getName());
            }
        }
    }

    @Override
    public String obtenerCampo(EtlCampoArchivoDto campoArchivoDto) {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(currentLine);
       //Cell.CELL_TYPE_NUMERIC;
        Cell cell =row.getCell(campoArchivoDto.getNumeroCampo()-1);  /**/
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                //System.out.print(cell.getNumericCellValue() + "(Integer)\t");
                return String.valueOf(cell.getNumericCellValue());

            case Cell.CELL_TYPE_STRING:
                //System.out.print(cell.getStringCellValue() + "(String)\t");
                return cell.getStringCellValue();
                default:
                    return null;

        }
    }

    public void getNextLine () {
        currentLine++;
    }

    @Override
    public List<ResGralDto> validarCamposArchivo() {

        List<ResGralDto> lstErrores = new ArrayList<>();

        archivoDto.getListaCampos().forEach(campoArchivoDto -> {
            String campo = obtenerCampo(campoArchivoDto);

            ResGralDto resGralDto = util.validarCampoArchivo(campoArchivoDto, campo, currentLine + 1, archivoDto.getExtension());

            if (resGralDto.getEstatus() == ApplicationConstants.ERR) {
                lstErrores.add(resGralDto);
            }
        });

        return  lstErrores;
    }

    @Override
    public void close() {
        try {
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
            workbook = null;
            currentLine = 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public int getCurrentLine() {
        return currentLine;
    }

    @Override
    public void setArchivoDto(EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public EtlArchivoDto getArchivoDto() {
        return this.archivoDto;
    }

    @Override
    public File getFile() {
        return this.file;
    }

}