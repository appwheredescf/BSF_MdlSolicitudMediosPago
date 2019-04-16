package mx.appwhere.mediospago.front.application.util;

import mx.appwhere.mediospago.front.application.dto.etl.EtlArchivoDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author JoseBarrios
 * @since 11/04/2019
 *
 */
public class InExcelFileProcessor implements FileProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InExcelFileProcessor.class);

    private Workbook workbook;

    private EtlArchivoDto archivoDto;

    private File file;

    private int currentLine;

    public InExcelFileProcessor(File file, EtlArchivoDto archivoDto) {
        this.archivoDto = archivoDto;
        this.file = file;
    }

    @Override
    public void open() {
        if (workbook == null) {
            try {
                workbook = WorkbookFactory.create(file);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public String getValueColumn(int nColumn) {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(currentLine);
        currentLine ++;
        return row.getCell(nColumn).getStringCellValue();
    }

    public Integer getRowNum() {
        if (workbook != null) {
            return  workbook.getSheetAt(0).getLastRowNum();
        }
        return null;
    }
    @Override
    public EtlArchivoDto getArchivoDto() {
        return this.archivoDto;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void close() {
        try {
            workbook.close();
            workbook = null;
            currentLine = 0;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}