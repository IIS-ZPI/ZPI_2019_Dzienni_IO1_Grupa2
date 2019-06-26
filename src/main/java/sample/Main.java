package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        if(runConnectionWindow()) {
            Parent root = FXMLLoader.load(getClass().getResource("..\\main_window.fxml"));
            primaryStage.setTitle("Gold and exchange rates");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) { launch(args); }

    private boolean runConnectionWindow() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Połączenie");
        alert.setHeaderText("Trwa łączenie z serwerem");
        alert.setContentText("Czy chcesz się połączyć?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK)
            return true;
        else
            return false;
    }
}
