package lab3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import lab1.Statistics;
import lab1.Tools;

import java.util.ArrayList;

public class Lab3 extends Application {
    private FlowPane root;

    private ComboBox<String> distributionsComboBox = new ComboBox<>();
    private Button btn = new Button();

    private TextField matOz = new TextField();
    private Label lblMatOz = new Label();
    private TextField dispersion = new TextField();
    private Label lblDispersion = new Label();
    private TextField matOzOtkl = new TextField();
    private Label lblMatOzOtkl = new Label();
    private TextField dispersOtkl = new TextField();
    private Label lblDispersOtkl = new Label();
    private TextField nField = new TextField();
    private Label nLbl = new Label();
    private TextField theoryMField = new TextField();
    private Label theoryMLbl = new Label();
    private TextField theoryDField = new TextField();
    private Label theoryDLbl = new Label();

    private LineChart<Number, Number> plotnRasprChart = null;
    private LineChart<Number, Number> funcRasprChart = null;

    public static void task(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Lab3");

        root = menu();
        configureUi();
        stage.setScene(new Scene(root, 1200, 900));
        stage.show();
    }

    private void configureUi() {
        matOz.setPrefWidth(70);
        dispersion.setPrefWidth(77);
        matOzOtkl.setPrefWidth(70);
        dispersOtkl.setPrefWidth(70);
        nField.setPrefWidth(70);
        theoryMField.setPrefWidth(70);
        theoryDField.setPrefWidth(77);

        btn.setText("Show");
        btn.setOnMouseClicked(this::action);
        lblMatOz.setText("M: ");
        theoryMLbl.setText("Theor");
        lblMatOzOtkl.setText("Otkl");
        lblDispersion.setText("D: ");
        theoryDLbl.setText("Theor");
        lblDispersOtkl.setText("Otkl");
        nLbl.setText("N");
    }

    private FlowPane menu() {
        ObservableList<String> distributions = FXCollections.observableArrayList(
                "rnuni", "rnnrm1", "rnnrm2", "rnexp", "rnchis", "rnstud"
        );
        distributionsComboBox.setItems(distributions);
        distributionsComboBox.setValue(distributions.get(0)); // устанавливаем выбранный элемент по умолчанию

        return new FlowPane(10, 10, distributionsComboBox, btn, nLbl, nField,
                lblMatOz, matOz, theoryMLbl, theoryMField, lblMatOzOtkl, matOzOtkl,
                lblDispersion, dispersion, theoryDLbl, theoryDField, lblDispersOtkl, dispersOtkl);
    }

    private void action(MouseEvent mouseEvent) {
        String method = distributionsComboBox.getValue();

        final int numPieces = 25;
//        final int numPieces = 12;
        double a = 0;
        double b = 0;
        int n = 10000;
        nField.setText(String.format("%d", n));

        ArrayList<Double> values;
        switch (method) {
            case "rnuni":
                a = 1;
                b = 100;
//                b = 10000;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnuni);
                theoryMField.setText(String.format("%f", lab3.Distributions.uniM));
                theoryDField.setText(String.format("%f", lab3.Distributions.uniD));
                break;
            case "rnnrm1":
                a = 10;
                b = 0.5;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnnrm1);
                theoryMField.setText(String.format("%f", lab3.Distributions.norm1M));
                theoryDField.setText(String.format("%f", lab3.Distributions.norm1D));
                break;
            case "rnnrm2":
                a = 0.5;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnnrm2);
                theoryMField.setText(String.format("%f", lab3.Distributions.norm2M));
                theoryDField.setText(String.format("%f", lab3.Distributions.norm2D));
                break;
            case "rnexp":
                a = 0.5;
                b = 1;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnexp);
                theoryMField.setText(String.format("%f", lab3.Distributions.expM));
                theoryDField.setText(String.format("%f", lab3.Distributions.expD));
                break;
            case "rnchis":
                a = 0.5;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnchis);
                theoryMField.setText(String.format("%f", lab3.Distributions.chiM));
                theoryDField.setText(String.format("%f", lab3.Distributions.chiD));
                break;
            case "rnstud":
                a = 10;
                values = lab3.Distributions.getValues(n, a, b, lab3.Distributions::rnstud);
                theoryMField.setText(String.format("%f", lab3.Distributions.studM));
                theoryDField.setText(String.format("%f", lab3.Distributions.studD));
                break;
            default:
                return;
        }

        // Tools and Statistics from lab1 package
        double mathExpect = Statistics.calculateMathExpect(values);
        double dispers = Statistics.calculateDispersion(values, mathExpect);
        matOz.setText(String.format("%f", mathExpect));
        matOzOtkl.setText(String.format("%f",
                Math.abs(mathExpect - Double.parseDouble(theoryMField.getText().replace(',', '.')))));
        dispersion.setText(String.format("%f", dispers));
        dispersOtkl.setText(String.format("%f",
                Math.abs(dispers - Double.parseDouble(theoryDField.getText().replace(',', '.')))));

        ArrayList<Double> distributionDensity = Statistics.calculateDistributionDensity(values, numPieces);
        ArrayList<Double> distributionFunc = Statistics.calculateDistributionFunc(distributionDensity);

        if (plotnRasprChart != null && funcRasprChart != null) {
            root.getChildren().removeAll(plotnRasprChart, funcRasprChart);
        }
//        plotnRasprChart = Tools.createLineChart("Plotn raspr", distributionDensity, values);
        plotnRasprChart = Tools.createLineChart("Plotn raspr", distributionDensity);
//        funcRasprChart = Tools.createLineChart("Func raspr", distributionFunc, values);
        funcRasprChart = Tools.createLineChart("Func raspr", distributionFunc);

        root.getChildren().addAll(plotnRasprChart, funcRasprChart);
    }
}
