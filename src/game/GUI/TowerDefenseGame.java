package game.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TowerDefenseGame extends Application {

    @FXML

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Tower Defense Game");
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene Scene = new Scene(root);
        primaryStage.setScene(Scene);
        Scene.getStylesheets().add(getClass().getResource("menustyle.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}