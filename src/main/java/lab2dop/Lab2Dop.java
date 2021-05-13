package lab2dop;

import lab2.Distributions;

import java.util.ArrayList;

public class Lab2Dop {

    public static void task() {
        final int n = 100;
        final double a = 10000;
        final double b = 0.5;
        ArrayList<Double> values = Distributions.getValues(n, a, b, Distributions::rnnorm);
    }
}
