package lab3;

import java.util.ArrayList;
import java.util.function.BiFunction;

public final class Distributions {
    /* theoretical values */
    public static double uniM = 50.5;
    public static double uniD = 833.25;

    public static double norm1M = 0.0;
    public static double norm1D = 1.0;

    public static double norm2M = 0.0;
    public static double norm2D = 1.0;

    public static double expM = 1.0;
    public static double expD = 1.0;

    public static double chiM = 10.0;
    public static double chiD = 20.0;

    public static double studM = 0.0;
    public static double studD = 1.25;

    public static ArrayList<Double> getValues(final int amount, final double r_low, final double r_up,
                                              BiFunction<Double, Double, Double> method) {
        ArrayList<Double> result = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            result.add(method.apply(r_low, r_up));
        }
        return result;
    }

    public static double rnuni(double a, double b){
        return Math.random() * (b - a) + a;
    }
    public static double rnnrm1(double a, double b) {
        return Math.sqrt(-2 * Math.log(Math.random()))
                * Math.cos(2 * Math.PI * Math.random());
    }
    public static double rnnrm2(double a, double b) {
        double r = 0.0;
        for (int i = 0; i < 12; i++)
            r += Math.random();
        return r - 6;
    }
    public static double rnexp(double a, double b) {
        return -b * Math.log(Math.random());
    }
    public static double rnchis(double a, double b) {
        double yn = 0.0;
        for (int i = 0; i < 10; i++) {
            double z = rnnrm1(a, b);
            yn += z * z;
        }
        return yn;
    }
    public static double rnstud(double a, double b) {
        return rnnrm1(a, b) / Math.sqrt(rnchis(a, b)/10.0);
    }
}
