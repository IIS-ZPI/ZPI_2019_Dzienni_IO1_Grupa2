package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> queriesComboBox;

    @FXML
    private ListView<String> listOfQueryParameters;
    ObservableList<String> list1 = FXCollections.observableArrayList();

    @FXML
    private Button doButton;

    @FXML
    private Button clearListButton;

    @FXML
    private Button exitButton;

    @FXML
    private ListView<String> listOfData;
    ObservableList<String> list2 = FXCollections.observableArrayList();

    @FXML
    public void comboBoxListener() {
        String selection = queriesComboBox.getSelectionModel().getSelectedItem();
        list1.add(selection);
        listOfQueryParameters.setItems(list1);

    }

    @FXML
    private void clearList(){
        list2.clear();
        listOfData.setItems(list2);
    }

    @FXML
    private void fillData(){
        list2.addAll(list1);
        listOfData.setItems(list2);
    }

    @FXML
    private void exit(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        queriesComboBox.getItems().addAll("query 1", "query 2", "query 3", "query 4", "query 5");
        queriesComboBox.getSelectionModel().select(1);

        listOfQueryParameters.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listOfQueryParameters.setItems(list1);
    }


}
