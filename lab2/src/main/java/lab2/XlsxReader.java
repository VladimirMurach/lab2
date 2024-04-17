package lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReader {

    public ArrayList<DescriptiveStatistics> read(File file, String sheetName, int sheetNumber) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet;
        if (sheetName == null) {
            sheet = workbook.getSheetAt(sheetNumber);
        } else {
            sheet = workbook.getSheet(sheetName);
        }
        ArrayList<DescriptiveStatistics> data = new ArrayList<>();
        for (Row row : sheet) {
            int i = 0;
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING ->
                        data.add(new DescriptiveStatistics());
                    case NUMERIC ->
                        data.get(i).addValue(cell.getNumericCellValue());
                    default -> {
                    }
                }
                i++;
            }
        }
        return data;
    }

    public ArrayList<String> readNames(File file, String sheetName, int sheetNumber) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet;
        if (sheetName == null) {
            sheet = workbook.getSheetAt(sheetNumber);
        } else {
            sheet = workbook.getSheet(sheetName);
        }
        ArrayList<String> names = new ArrayList<>();
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            names.add(cell.getStringCellValue());
        }
        workbook.close();
        return names;
    }
    
}
