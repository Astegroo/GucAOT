package game.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public Button easyMode;
    public Button hardMode;
    public Stage stage;
    public Scene scene;


    public void SwitchToEasy(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("gameEasy.fxml"));
        } catch (IOException e) {
            errorMessage("Couldnt load FXML!");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gamestyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToHard(ActionEvent event)  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("gameHard.fxml"));
        } catch (IOException e) {
            errorMessage("Couldn't load FXML");
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gamestyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void errorMessage(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);

        error.setHeaderText("Something Wrong Happened!");
        error.setContentText(msg);
        error.showAndWait();

    }

}
