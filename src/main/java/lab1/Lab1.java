package lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
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
        FlowPane root = new FlowPane();
        root.getChildren().addAll(Tools.createLineChart("Distribution Density", distributionDensity),
                                  Tools.createLineChart("Distribution Function", distributionFunc));

        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }
}
