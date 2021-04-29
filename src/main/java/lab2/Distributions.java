package lab2;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiFunction;

public final class Distributions {
    /* theoretical values */
    public static double uniM = 50.5;
    public static double uniD = 833.25;

    public static double binM = 5.0;
    public static double binD = 2.5;

    public static double geoM = 2.0;
    public static double geoD = 2.2;

    public static double poiM = 10.0;
    public static double poiD = 10.0;

    public static double psnM = 10.0;
    public static double psnD = 10.0;

    public static double logM = 1.4427;
    public static double logD = 0.80402;

    public static ArrayList<Double> getValues(final int amount, final double r_low, final double r_up,
                                              BiFunction<Double, Double, Integer> method) {
        ArrayList<Double> result = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            result.add((double) method.apply(r_low, r_up));
        }
        return result;
    }

    private static double gaussrand(final double mo, final double sko) {
        double sum = 0.0;
        double x;
        for (int i = 0; i < 28; i++) {
            sum += Math.random();
        }
        return (Math.sqrt(2.0) * sko * (sum - 14.)) / 2.11233 + mo;
    }

    public static Integer irnuni(final double ilow, final double iup) {
        if (ilow > iup) throw new IllegalArgumentException("ilow > iup!");
        return (int) Math.floor((iup - ilow + 1) * new Random().nextDouble() + ilow);
    }

    public static Integer rnnorm(final double a_, final double p) {
        int n = (int) Math.round(a_);
        if (n >= 100) {
            return (int) Math.round(gaussrand(n * p, Math.sqrt(n * p * (1.0 - p))) + 0.5);
        } else {
            double a = Math.random();
            double temp = Math.pow(1 - p, n);
            int m = 0;
            while (a - temp >= 0) {
                a -= temp;
                temp *= ((p * (n - m)) / ((m + 1) * (1 - p)));
                m++;
            }
            return m;
        }
    }

    public static Integer irngeo1(final double p, final double b_) {
        double a = Math.random();
        double temp = p;
        int m = 0;
        while (a - temp >= 0) {
            a -= temp;
            temp *= (1 - p);
            m++;
        }
        return m + 1;
    }

    public static Integer irngeo2(final double p, final double b_) {
        double a = Math.random();
        int m = 0;
        while (a > p) {
            a = Math.random();;
            m++;
        }
        return m + 1;
    }

    public static Integer irngeo3(final double p, final double b_) {
        double a = Math.random();
        return (int)(Math.log(a) / Math.log(1 - p)) + 1;
    }

    public static Integer irnpoi(final double mu, final double b_) {
        if (mu < 88) {
            double a = Math.random();
            double temp = Math.exp(-mu);
            int m = 1;
            while (a - temp >= 0) {
                a -= temp;
                temp *= mu / m;
                m++;
            }
            return m;
        } else {
            return (int) Math.round(gaussrand(mu, mu));
        }
    }

    public static Integer irnpsn(final double mu, final double b_) {
        if (mu < 88) {
            double a = Math.random();
            double temp = a;
            int m = 1;
            while (temp >= Math.exp(-mu)) {
                a = Math.random();
                temp *= a;
                m++;
            }
            return m;
        } else {
            return (int) Math.round(gaussrand(mu, mu));
        }
    }

    public static Integer irnlog(final double q, final double b_) {
        double a = Math.random();
        double temp = -(1.0 / Math.log(q)) * (1 - q);
        int m = 1;
        while (a - temp >= 0) {
            a -= temp;
            temp *= (m * 1.0 / (m + 1)) * (1 - q);
            m++;
        }
        return m;
    }
}