package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @Override public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1", 5));
        series.getData().add(new XYChart.Data("2", 2.92));
        series.getData().add(new XYChart.Data("3", 3.96));
        series.getData().add(new XYChart.Data("4", 0.05));
        series.getData().add(new XYChart.Data("5", 6.03));

        LineChart.getData().addAll(series);
    }
}
