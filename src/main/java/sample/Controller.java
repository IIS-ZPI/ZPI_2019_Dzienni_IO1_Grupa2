package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.api.ApiConnector;
import sample.api.entities.CurrencyRates;
import sample.api.entities.CurrencyRatesContainer;
import sample.data_providers.DataProviderFactory;
import sample.data_providers.IDataProvider;

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
    private ComboBox<String> periodComboBox;
    ObservableList<String> periods = FXCollections.observableArrayList("1 tydzień", "2 tygodnie", "1 miesiąc", "3 miesiące", "6 miesięcy", "rok");

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
    ObservableList<String> parameters2 = FXCollections.observableArrayList("Mediana", "Dominanta", "Odchylenie standardowe", "Współczynnik zmienności");
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
    private void exit(){
        System.exit(0);
    }

    @FXML
    private void executeQuery(){
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
            CurrencyRatesContainer[] container = api.RequestTopExchangeRates("A", 1);
            CurrencyRatesContainer[] container2 = api.RequestTopExchangeRates("B", 1);
            //String lines[] = string.split("\\r?\\n");
            listOfData.getItems().addAll(container[0].toString());
            listOfData.getItems().addAll(container2[0].toString());

        }*/
    }

    @FXML
    private void clearList(){
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
    }

    private void setListViewContent(String query){
        listOfQueryParameters.getItems().clear();

        if(query == queries[0])
            listOfQueryParameters.getItems().addAll(parameters1);

        if(query == queries[1])
            listOfQueryParameters.getItems().addAll(parameters2);

        if(query == queries[2])
            listOfQueryParameters.getItems().addAll(parameters3);
    }

    @FXML
    private void drawChart(){
        /*
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("..\\line_chart.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Chart");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    private void fillCurrencyComboBox(){
        /*CurrencyRatesContainer[] container = api.RequestTopExchangeRates("A", 1);
        CurrencyRatesContainer[] container2 = api.RequestTopExchangeRates("B", 1);
        for(int i=0; i<container[0].getRates().length; i++){
            currency.add(container[0].getRates()[i].getCode() + " - " + container[0].getRates()[i].getCurrency());
        }
        for(int i=0; i<container2[0].getRates().length; i++){
            currency.add(container2[0].getRates()[i].getCode() + " - " + container2[0].getRates()[i].getCurrency());
        }*/
    }


}
