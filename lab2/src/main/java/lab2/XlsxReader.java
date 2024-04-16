package lab2;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReader {

    public ArrayList<DescriptiveStatistics> read(File file) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
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

    public ArrayList<String> readNames(File file) throws IOException, InvalidFormatException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        ArrayList<String> names = new ArrayList<>();
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            names.add(cell.getStringCellValue());
        }
        workbook.close();
        return names;
    }
}
