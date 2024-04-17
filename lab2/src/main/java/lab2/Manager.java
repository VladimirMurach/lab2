package lab2;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Manager {

    private Storage storage = new Storage();
    private Calculator calculator = new Calculator();
    private XlsxReader reader = new XlsxReader();
    private XlsxWriter writer = new XlsxWriter();
    private String sheetName = null;
    private int sheetNumber = 0;

    void importFile(File file) throws IOException, InvalidFormatException {
        storage.setImportData(reader.read(file, sheetName, sheetNumber));
        storage.setSampleNames(reader.readNames(file, sheetName, sheetNumber));
        storage.toDoubleArray();
        System.out.println("Импорт завершен!");
        sheetName = null;
    }

    void exportFile() throws IOException {
        calculate();
        writer.write(storage.getExportData(), storage.getSampleNames(), storage.getCovExportData());
        System.out.println("Экспорт завершен!");
    }

    void calculate() {
        storage.setExportData(calculator.calculate(storage.getImportData()));
        storage.setCovExportData(calculator.covariance(storage.getCovImportData()));
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setSheetNumber(String sheetName) {
        try {
            sheetNumber = Integer.parseInt(sheetName);
        } catch(NumberFormatException e)  {
            System.out.println("Введено не число");
        }
    }

}
