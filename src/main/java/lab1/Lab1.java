package lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Lab1 extends Application {
    public static void task(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int numPieces = 12;
        final int n = 10000;
        final String path = "lab1.txt";

        ArrayList<Double> values = Statistics.getRandomValues(n);
        double mathExpect = Statistics.calculateMathExpect(values);
        double dispersion = Statistics.calculateDispersion(values, mathExpect);
        ArrayList<Double> autoCorrelation =
                Statistics.calculateCorrelationFunc(values, mathExpect, dispersion);

        System.out.println("Math expectation, n=" + n + ": " + mathExpect);
        System.out.println("Dispersion, n=" + n + ": " + dispersion);
        Tools.printListToFile(autoCorrelation, path);

        ArrayList<Double> distributionDensity = Statistics.calculateDistributionDensity(values, numPieces);
        ArrayList<Double> distributionFunc = Statistics.calculateDistributionFunc(distributionDensity);

        primaryStage.setTitle("Charts");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChartDens = new LineChart<>(xAxis,yAxis);
//        final LineChart<Number,Number> lineChartFunc = new LineChart<>(xAxis,yAxis);

        lineChartDens.setTitle("Distribution Density");
        XYChart.Series seriesDens = new XYChart.Series();
        seriesDens.setName("Distribution Density");

//        lineChartFunc.setTitle("Distribution Function");
//        XYChart.Series seriesFunc = new XYChart.Series();
//        seriesFunc.setName("Distribution Function");

        //populating the series with data
        for (int i = 0; i < numPieces; ++i) {
            seriesDens.getData().add(new XYChart.Data((i + 1) / 12.0, distributionDensity.get(i)));
//            seriesFunc.getData().add(new XYChart.Data((i + 1) / 12.0, distributionFunc.get(i)));
        }

        Scene scene  = new Scene(lineChartDens, 800, 600);
//        Scene scene  = new Scene(lineChartFunc, 800, 600);
        lineChartDens.getData().add(seriesDens);
//        lineChartFunc.getData().add(seriesFunc);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
