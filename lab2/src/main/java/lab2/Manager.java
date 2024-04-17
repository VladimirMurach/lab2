package lab2;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Manager {
    
    Storage storage = new Storage();
    Calculator calculator = new Calculator();
    XlsxReader reader = new XlsxReader();
    XlsxWriter writer = new XlsxWriter();

    void importFile(File file) throws IOException, InvalidFormatException {
        storage.setImportData(reader.read(file));
        storage.setSampleNames(reader.readNames(file));
        storage.toDoubleArray();
        System.out.println("Импорт завершен!");
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
    
}
