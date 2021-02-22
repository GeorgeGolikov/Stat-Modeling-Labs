package lab1;

import java.util.ArrayList;
import java.util.Random;

public final class Statistics {
    public static ArrayList<Double> getRandomValues(int amount) {
        Random rnd = new Random();
        ArrayList<Double> result = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            result.add(rnd.nextDouble());
        }
        return result;
    }

    public static double calculateMathExpect(ArrayList<Double> values) {
        double sum = 0.0;
        for (Double val : values) {
            sum += val;
        }
        return sum / values.size();
    }

    public static double calculateDispersion(ArrayList<Double> values, double mathExpect) {
        double sum = 0.0;
        for (Double val : values) {
            sum += Math.pow(val - mathExpect, 2);
        }
        return sum / values.size();
    }

    public static double calculateMeanSquare(double dispersion) {
        return Math.sqrt(dispersion);
    }

    public static ArrayList<Double> calculateCorrelationFunc(ArrayList<Double> values,
                                                             double mathExpect, double dispersion) {
        int size = values.size();
        double denominator = dispersion * size;
        ArrayList<Double> result = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            double numerator = 0.0;
            for (int j = 0; j < size - i; ++j) {
                numerator += (values.get(j) - mathExpect) * (values.get(j + i) - mathExpect);
            }
            result.add(numerator / denominator);
        }
        return result;
    }
}
