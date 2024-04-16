package lab2;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Storage {

    private ArrayList<DescriptiveStatistics> importData;
    private ArrayList<ArrayList<Double>> exportData;
    private ArrayList<String> sampleNames;

    public ArrayList<String> getSampleNames() {
        return sampleNames;
    }

    public void setSampleNames(ArrayList<String> sampleNames) {
        this.sampleNames = sampleNames;
    }

    void setImportData(ArrayList<DescriptiveStatistics> importData) {
        this.importData = importData;
    }

    public ArrayList<DescriptiveStatistics> getImportData() {
        return importData;
    }

    public ArrayList<ArrayList<Double>> getExportData() {
        return exportData;
    }

    public void setExportData(ArrayList<ArrayList<Double>> exportData) {
        this.exportData = exportData;
    }
    
}
