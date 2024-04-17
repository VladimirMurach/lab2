package lab2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxWriter {

    private final String[] HEADER = new String[]{"Выборка", "Среднее арифметическое", "Среднее геометрическое", "Стандартное отклонение", "Размах", "Количество элементов", "Коэффициент вариации", "Дисперсия", "Максимум", "Минимум", "LCI", "UCI"};

    public void write(ArrayList<ArrayList<Double>> data, ArrayList<String> names, RealMatrix covData) throws FileNotFoundException, IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Statistics");
        
        Row header = sheet.createRow(0);
        int i = 0;
        for (String name : HEADER) {
            header.createCell(i).setCellValue(name);
            i++;
        }
        
        i = 1;
        for (ArrayList<Double> sample : data) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(names.get(i-1));
            int j = 1;
            for (Double value : sample) {
                Cell cell = row.createCell(j);
                cell.setCellValue(value);
                j++;
            }
            i++;
        }
        
        i++;
        sheet.createRow(i).createCell(0).setCellValue("Ковариационная матрица");
        
        i++;
        Row row = sheet.createRow(i);
        int j = 1;
        for (String name : names) {
            row.createCell(j).setCellValue(name);
            j++;
        }
        
        i++;
        for (int k = 0; k < covData.getColumnDimension(); k++) {
            row = sheet.createRow(i+k);
            row.createCell(0).setCellValue(names.get(k));
            double[] col = covData.getColumn(k);
            for (int l = 0; l < covData.getRowDimension(); l++) {
                row.createCell(l+1).setCellValue(col[l]);
            }
        }
        
        FileOutputStream fos = new FileOutputStream("result.xlsx");
        workbook.write(fos);
        workbook.close();
    }
}
