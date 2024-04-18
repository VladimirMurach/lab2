package lab2;

import java.util.ArrayList;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Storage {

    private ArrayList<DescriptiveStatistics> importData;
    private ArrayList<ArrayList<Double>> exportData;
    private ArrayList<String> sampleNames;
    private double[][] covImportData;
    private RealMatrix covExportData;

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

    public double[][] getCovImportData() {
        return covImportData;
    }

    public void setCovImportData(double[][] covImportData) {
        this.covImportData = covImportData;
    }

    public RealMatrix getCovExportData() {
        return covExportData;
    }

    public void setCovExportData(RealMatrix covExportData) {
        this.covExportData = covExportData;
    }

    public void toDoubleArray() {
        try {
            covImportData = new double[(int) importData.get(0).getN()][importData.size()];
            int i = 0;
            for (DescriptiveStatistics sample : importData) {
                for (int j = 0; j < sample.getN(); j++) {
                    covImportData[j][i] = sample.getElement(j);
                }
                i++;
            }
            System.out.println("Импорт завершен!");
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
