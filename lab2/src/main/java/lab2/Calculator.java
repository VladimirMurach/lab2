package lab2;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Calculator {

    public ArrayList<ArrayList<Double>> calculate(ArrayList<DescriptiveStatistics> importData) {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        for (int i = 0; i < importData.size(); i++) {
            result.add(new ArrayList<>());
            ArrayList<Double> iResult = result.get(i);
            DescriptiveStatistics sample = importData.get(i);
            iResult.add(sample.getMean());
            iResult.add(sample.getGeometricMean());
            iResult.add(sample.getStandardDeviation());
            iResult.add(sample.getMax()-sample.getMin());
            iResult.add((double) sample.getN());
            iResult.add(sample.getStandardDeviation()/sample.getMean());
            iResult.add(sample.getVariance());
            iResult.add(sample.getMax());
            iResult.add(sample.getMin());
            iResult.add(sample.getMean() - 1.96*sample.getStandardDeviation()/sqrt(sample.getN()));
            iResult.add(sample.getMean() + 1.96*sample.getStandardDeviation()/sqrt(sample.getN()));
        }
        return result;
    }

    public RealMatrix covariance(double[][] covImportData) {
        Covariance cov = new Covariance(covImportData);
        return cov.getCovarianceMatrix();
    }
    
}
