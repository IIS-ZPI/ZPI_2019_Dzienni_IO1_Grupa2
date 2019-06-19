package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.api.entities.CurrencyRates;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> queriesComboBox;
    String[] queries = {"Ilość sesji wzrostowych, spadkowych i bez zmian",
                    "Miary statystyczne(mediana, dominanta, odchylenie standardowe, współczynnik zmienności",
                    "Rozkład zmian miesięcznych i kwartalnych dla par walutowych"};
    @FXML
    private ComboBox<String> currencyComboBox;
    ObservableList<String> currency = FXCollections.observableArrayList("USD", "PLN", "CHF");

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
        if(!listOfQueryParameters.getSelectionModel().isEmpty() && !currencyComboBox.getSelectionModel().isEmpty() && !periodComboBox.getSelectionModel().isEmpty()) {
            listOfData.getItems().clear();
            queryValue = listOfQueryParameters.getSelectionModel().getSelectedItem()
                         + "  " + currencyComboBox.getSelectionModel().getSelectedItem()
                         + " za okres " + periodComboBox.getSelectionModel().getSelectedItem();
            queryValueList.add(queryValue);
            listOfData.getItems().addAll(queryValueList);
        }
    }

    @FXML
    private void clearList(){
        queryValueList.clear();
        listOfData.getItems().clear();
        listOfData.getItems().addAll(queryValueList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    }


}
