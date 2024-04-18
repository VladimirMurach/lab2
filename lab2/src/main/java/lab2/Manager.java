package lab2;

import java.io.File;
import java.io.IOException;

public class Manager {

    private Storage storage = new Storage();
    private Calculator calculator = new Calculator();
    private XlsxReader reader = new XlsxReader();
    private XlsxWriter writer = new XlsxWriter();
    private String sheetName = null;
    private int sheetNumber = 0;

    public void importFile(File file) {
        reader.openFile(file, sheetName, sheetNumber);
        storage.setImportData(reader.read());
        storage.setSampleNames(reader.readNames());
        storage.toDoubleArray();
        sheetName = null;
    }

    public void exportFile() throws IOException {
        calculate();
        writer.write(storage.getExportData(), storage.getSampleNames(), storage.getCovExportData());
        System.out.println("Экспорт завершен!");
    }

    public void calculate() {
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
