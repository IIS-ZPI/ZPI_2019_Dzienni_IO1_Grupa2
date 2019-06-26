package currency;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    @FXML private LineChart<?, ?> LineChart;

    @FXML private CategoryAxis x;

    @FXML private NumberAxis y;

    private List<Double> valuesY;//= {5, 2.92, 3.96, 0.05, 6.03};

    @Override public void initialize(URL location, ResourceBundle resources) {
        //XYChart.Series series = createSeries();
        //LineChart.getData().addAll(series);
    }

    private XYChart.Series createSeries() {
        XYChart.Series series = new XYChart.Series();
        for (int i = 1; i < valuesY.size(); i++)
            series.getData().add(new XYChart.Data(Integer.toString(i - 1), valuesY.get(i)));
        return series;
    }

    public void drawLineChart(List<Double> values) {
        this.valuesY = values;
        XYChart.Series series = createSeries();
        LineChart.getData().addAll(series);
    }
}
