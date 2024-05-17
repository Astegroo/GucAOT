package game.GUI;
import game.engine.Battle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class TowerDefenseGame extends Application {

    @FXML

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tower Defense Game");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        } catch (IOException e) {
            errorMessage("FXML Didnt load correctly");
        }
        Scene Scene = new Scene(root);
        primaryStage.setScene(Scene);
        Scene.getStylesheets().add(getClass().getResource("menustyle.css").toExternalForm());
        primaryStage.show();
    }
    public void errorMessage(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);

        error.setHeaderText("Something Wrong Happened!");
        error.setContentText(msg);
        error.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }
}