package lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReader {

    private Sheet sheet;

    public ArrayList<DescriptiveStatistics> read() {
        ArrayList<DescriptiveStatistics> data = new ArrayList<>();
        try {
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
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Листа не существует");
        }

        return data;
    }

    public ArrayList<String> readNames() {
        ArrayList<String> names = new ArrayList<>();
        try {
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            names.add(cell.getStringCellValue());
        }
        } catch (NullPointerException | IllegalArgumentException e) {
            
        }
        return names;
    }

    public void openFile(File file, String sheetName, int sheetNumber) {
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(file);
            if (sheetName == null) {
                sheet = workbook.getSheetAt(sheetNumber);
            } else {
                sheet = workbook.getSheet(sheetName);
            }
        } catch (NotOfficeXmlFileException | POIXMLException e) {
            System.out.println("Выбран не Excel файл");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Листа не существует");
        } catch (InvalidFormatException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                workbook.close();
            } catch (Exception e) {

            }
        }
    }
}
