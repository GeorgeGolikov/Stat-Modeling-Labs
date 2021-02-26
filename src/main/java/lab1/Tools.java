package lab1;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Tools {
    public static void printListToFile(ArrayList<Double> list, String path) {
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file, false); // overwrites the file
            for (double d : list){
                writer.write(String.valueOf(d).replace(".", ",") + "\n");
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static LineChart<Number,Number> createLineChart(String name, ArrayList<Double> data) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle(name);
        XYChart.Series series = new XYChart.Series();
        series.setName(name);

        //populating the series with data
        for (int i = 0; i < data.size(); ++i) {
            series.getData().add(new XYChart.Data((i + 1) / ((double) data.size()), data.get(i)));
        }

        lineChart.getData().add(series);

        return lineChart;
    }
}
