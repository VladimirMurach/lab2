package lab2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxWriter {

    private final String[] HEADER = new String[]{"Выборка", "Среднее арифметическое", "Среднее геометрическое", "Стандартное отклонение", "Размах", "Количество элементов", "Коэффициент вариации", "Дисперсия", "Максимум", "Минимум", "LCI", "UCI"};

    public void write(ArrayList<ArrayList<Double>> data, ArrayList<String> names) throws FileNotFoundException, IOException {
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
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Владимир\\Desktop\\result.xlsx");
        workbook.write(fos);
        workbook.close();
    }
}
