package currency;

import currency.data_providers.DataProviderFactory;
import currency.data_providers.IDataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IDataProvider provider = DataProviderFactory.GetDefaultDataProvider();

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
        /*if(!listOfQueryParameters.getSelectionModel().isEmpty() && !currencyComboBox.getSelectionModel().isEmpty() && !periodComboBox.getSelectionModel().isEmpty()) {
            listOfData.getItems().clear();
            queryValue = listOfQueryParameters.getSelectionModel().getSelectedItem()
                         + "  " + currencyComboBox.getSelectionModel().getSelectedItem()
                         + " za okres " + periodComboBox.getSelectionModel().getSelectedItem();
            queryValueList.add(queryValue);
            listOfData.getItems().addAll(queryValueList);
        }
        if(listOfQueryParameters.getSelectionModel().isEmpty() && currencyComboBox.getSelectionModel().isEmpty() && periodComboBox.getSelectionModel().isEmpty()) {
            listOfData.getItems().clear();
            CurrenciesTopRatesContainer[] container = api.RequestTopExchangeRates("A", 1);
            CurrenciesTopRatesContainer[] container2 = api.RequestTopExchangeRates("B", 1);
            //String lines[] = string.split("\\r?\\n");
            listOfData.getItems().addAll(container[0].toString());
            listOfData.getItems().addAll(container2[0].toString());

        }*/
        PeriodEnum period = setPeriod(periodComboBox.getSelectionModel().getSelectedItem());
        String currency1 = currencyComboBox.getSelectionModel().getSelectedItem();
        String currency2 = currencyComboBox2.getSelectionModel().getSelectedItem();
        String query = listOfQueryParameters.getSelectionModel().getSelectedItem();
        double result = -999;
        if (!query.isEmpty() && !currency1.isEmpty() && !periodComboBox.getSelectionModel().getSelectedItem().isEmpty()) {

            listOfData.getItems().clear();

            if (query == parameters1.get(0))
                result = provider.getSessionIncreaseAmount(period, currency1);
            if (query == parameters1.get(1))
                result = provider.getSessionDecreaseAmount(period, currency1);
            if (query == parameters1.get(2))
                result = provider.getSessionWithoutChangeAmount(period, currency1);
            if (query == parameters2.get(0))
                result = provider.getMedianOfRate(period, currency1);
            //if (query == parameters2.get(1))
                //result = provider.getDominantOfRate(period, currency1);
            if (query == parameters2.get(2))
                result = provider.getStandardDevationOfRate(period, currency1);
            if (query == parameters2.get(3))
                result = provider.getCoefficientOfVariationOfRate(period, currency1);
            if (!currency2.isEmpty()) {
                //if (query == parameters3.get(0))
                //result = provider.GetMonthlyDistributionOfChanges(currency1, currency2);
                //if (query == parameters3.get(1))
                //result = provider.GetQuarterDistributionOfChanges(currency1, currency2);
            }
            queryValue = query + " / Waluta: " + currency1 + " / Za okres: " + period + " - " + result;
            queryValueList.add(queryValue);
            listOfData.getItems().addAll(queryValueList);
        }

    }

    @FXML
    private void clearList() {
        queryValueList.clear();
        listOfData.getItems().clear();
        listOfData.getItems().addAll(queryValueList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCurrencyComboBox();
        queriesComboBox.getItems().addAll(queries[0], queries[1], queries[2]);
        periodComboBox.getItems().addAll(periods);
        currencyComboBox.getItems().addAll(currency);
        currencyComboBox2.getItems().addAll(currency);
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

        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("..\\line_chart.fxml"));
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(queryValue);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fillCurrencyComboBox(){
        /*CurrenciesTopRatesContainer[] container = api.RequestTopExchangeRates("A", 1);
        CurrenciesTopRatesContainer[] container2 = api.RequestTopExchangeRates("B", 1);
        for(int i=0; i<container[0].getRates().length; i++){
            currency.add(container[0].getRates()[i].getCode() + " - " + container[0].getRates()[i].getCurrency());
        }
        for(int i=0; i<container2[0].getRates().length; i++){
            currency.add(container2[0].getRates()[i].getCode() + " - " + container2[0].getRates()[i].getCurrency());
        }*/
    }

    private PeriodEnum setPeriod(String s) {
        //"1 tydzień", "2 tygodnie", "1 miesiąc", "3 miesiące", "6 miesięcy", "rok"
        if (s == "1 tydzień")
            return PeriodEnum.PERIOD_WEEK;
        if (s == "2 tygodnie")
            return PeriodEnum.PERIOD_TWO_WEEKS;
        if (s == "1 miesiąc")
            return PeriodEnum.PERIOD_MONTH;
        if (s == "3 miesiące")
            return PeriodEnum.PERIOD_QUARTER;
        if (s == "6 miesięcy")
            return PeriodEnum.PERIOD_HALF_OF_YEAR;
        if (s == "rok")
            return PeriodEnum.PERIOD_YEAR;
        else
            return null;
    }

}
