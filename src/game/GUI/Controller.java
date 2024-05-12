package game.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public Button easyMode;
    public Button hardMode;
    public Stage stage;
    public Scene scene;


    public void SwitchToMenu(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    public void SwitchToEasy(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameEasy.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gamestyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToHard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameHard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("gamestyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void StartEasyMode(ActionEvent event) throws IOException{
    SwitchToEasy(event);

    }

}
