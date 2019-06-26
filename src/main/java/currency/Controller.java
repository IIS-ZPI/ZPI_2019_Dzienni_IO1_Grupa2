package currency;

import currency.data_providers.DataProviderFactory;
import currency.data_providers.IDataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IDataProvider provider = DataProviderFactory.getDefaultDataProvider();

    @FXML
    private ComboBox<String> queriesComboBox;
    String[] queries = {"Ilość sesji wzrostowych, spadkowych i bez zmian",
            "Miary statystyczne(mediana, dominanta, odchylenie standardowe, współczynnik zmienności",
            "Rozkład zmian miesięcznych i kwartalnych dla par walutowych"};
    @FXML
    private ComboBox<String> currencyComboBox;
    ObservableList<String> currency = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> currencyComboBox2;

    @FXML
    private ComboBox<String> periodComboBox;
    ObservableList<String> periods = FXCollections.observableArrayList("1 tydzień", "2 tygodnie", "1 miesiąc", "3 miesiące", "6 miesięcy",
            "rok");

    @FXML
    private Button executeButton;

    @FXML
    private Button clearListButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button drawChartButton;

    @FXML
    private ListView<String> listOfQueryParameters;
    ObservableList<String> parameters1 = FXCollections.observableArrayList("Sesje wzrostowe", "Sesje spadkowe", "Sesje bez zmian");
    ObservableList<String> parameters2 = FXCollections.observableArrayList("Mediana", "Dominanta", "Odchylenie standardowe",
            "Współczynnik zmienności");
    ObservableList<String> parameters3 = FXCollections.observableArrayList("Rozkład zmian miesięcznych", "Rozklad zmian kwartalnych");

    @FXML
    private ListView<String> listOfData;
    String queryValue;
    ObservableList<String> queryValueList = FXCollections.observableArrayList();

    private List<Double> chartValues;
    private String chartTitle;

    @FXML
    public void comboBoxListener() {
        setListViewContent(queriesComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void exit() {
        System.exit(0);
    }

    @FXML
    private void executeQuery() {
        String periodString = periodComboBox.getSelectionModel().getSelectedItem();
        PeriodEnum period = setPeriod(periodString);
        String currency1 = currencyComboBox.getSelectionModel().getSelectedItem();
        String currency2 = currencyComboBox2.getSelectionModel().getSelectedItem();
        String query = listOfQueryParameters.getSelectionModel().getSelectedItem();
        List<Double> resultList = null;
        double resultDouble = 0;
        int resultInt = 0;
        String resultType = null;

        listOfData.getItems().clear();
        clearList();

        if (query == parameters1.get(0)) {
            resultInt = provider.getSessionIncreaseAmount(period, currency1);
            resultType = "int";
        }
        if (query == parameters1.get(1)) {
            resultInt = provider.getSessionDecreaseAmount(period, currency1);
            resultType = "int";
        }
        if (query == parameters1.get(2)) {
            resultInt = provider.getSessionWithoutChangeAmount(period, currency1);
            resultType = "int";
        }
        if (query == parameters2.get(0)) {
            resultDouble = provider.getMedianOfRate(period, currency1);
            resultType = "double";
        }
        if (query == parameters2.get(1)) {
            resultList = provider.getDominantOfRate(period, currency1);
            resultType = "list";
        }
        if (query == parameters2.get(2)) {
            resultDouble = provider.getStandardDevationOfRate(period, currency1);
            resultType = "double";
        }
        if (query == parameters2.get(3)) {
            resultDouble = provider.getCoefficientOfVariationOfRate(period, currency1);
            resultType = "double";
        }
        if (query == parameters3.get(0)) {
            resultList = provider.getMonthlyDistributionOfChanges(currency1, currency2);
            chartValues = resultList;
            resultType = "list";
        }
        if (query == parameters3.get(1)) {
            resultList = provider.getQuarterDistributionOfChanges(currency1, currency2);
            chartValues = resultList;
            resultType = "list";
        }
        if (resultType == "list") {
            if (query != parameters3.get(0) && query != parameters3.get(1))
                queryValue = query + " / Waluta: " + currency1 + " \nZa okres: " + periodString + "\nWartość:\t\t" + resultList.toString();
            else {
                queryValue = query
                        + " / Dla walut: "
                        + currency1
                        + " & "
                        + currency2
                        + " \nZa okres: "
                        + periodString
                        + "\nWartość:\t\t"
                        + resultList.toString();
                chartTitle = query + " / Dla walut: " + currency1 + " & " + currency2 + " \nZa okres: " + periodString;
            }
        } else if (resultType == "double") {
            queryValue = query + " / Waluta: " + currency1 + " \nZa okres: " + periodString + "\nWartość:\t\t" + resultDouble;
        } else if (resultType == "int") {
            queryValue = query + " / Waluta: " + currency1 + " \nZa okres: " + periodString + "\nWartość:\t\t" + resultInt;
        }
        queryValueList.add(queryValue);
        listOfData.getItems().addAll(queryValueList);

    }

    @FXML
    private void clearList() {
        queryValueList.clear();
        listOfData.getItems().clear();
        listOfData.getItems().addAll(queryValueList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCurrencyList();
        queriesComboBox.getItems().addAll(queries[0], queries[1], queries[2]);
        periodComboBox.getItems().addAll(periods);
        currencyComboBox.getItems().addAll(currency);
        currencyComboBox2.getItems().addAll(currency);
        periodComboBox.getSelectionModel().selectFirst();
        currencyComboBox.getSelectionModel().select(1);
        currencyComboBox2.getSelectionModel().select(7);
    }

    private void setListViewContent(String query) {
        listOfQueryParameters.getItems().clear();

        if (query == queries[0])
            listOfQueryParameters.getItems().addAll(parameters1);

        if (query == queries[1])
            listOfQueryParameters.getItems().addAll(parameters2);

        if (query == queries[2])
            listOfQueryParameters.getItems().addAll(parameters3);
    }

    @FXML
    private void drawChart() {
        String selectedValue = listOfData.getSelectionModel().getSelectedItems().toString();
        try {
            if (selectedValue.contains(parameters3.get(0)) || selectedValue.contains(parameters3.get(1))) {
                //Parent root1 = FXMLLoader.load(getClass().getResource("..\\line_chart.fxml"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\line_chart.fxml"));
                Parent root1 = (Parent) loader.load();
                ChartController chartController = loader.getController();
                chartController.drawLineChart(chartValues);
                Stage stage = new Stage();
                stage.setTitle(chartTitle);
                stage.setScene(new Scene(root1));
                stage.show();
            } else {
                showAlertWindow();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private PeriodEnum setPeriod(String s) {
        if (s == periods.get(0))
            return PeriodEnum.PERIOD_WEEK;
        if (s == periods.get(1))
            return PeriodEnum.PERIOD_TWO_WEEKS;
        if (s == periods.get(2))
            return PeriodEnum.PERIOD_MONTH;
        if (s == periods.get(3))
            return PeriodEnum.PERIOD_QUARTER;
        if (s == periods.get(4))
            return PeriodEnum.PERIOD_HALF_OF_YEAR;
        if (s == periods.get(5))
            return PeriodEnum.PERIOD_YEAR;
        else
            return null;
    }

    private void initCurrencyList() {
        String[] cr = {"THB", "USD", "AUD", "HKD", "CAD", "NZD", "SGD", "EUR", "HUF", "CHF", "GBP", "UAH", "JPY", "CZK", "DKK", "NOK",
                "SEK", "HRK", "RON", "BGN", "TRY", "ILS", "CLP", "PHP", "MXN", "ZAR", "BRL", "MYR", "RUB", "IDR", "INR", "KRW", "CNY",
                "XDR"};
        currency.addAll(cr);
    }

    private void showAlertWindow() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd podczas rysowania wykresu");
        alert.setContentText("Nie można narysować wykresu dla wybranego zapytania");
        alert.showAndWait();
    }

}
