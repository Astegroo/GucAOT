package game.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TowerDefenseGame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("../../sample.fxml"));
            Scene Scene = new Scene(root);


            primaryStage.setTitle("AOT Tower Defense");
            Image icon = new Image("icons.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setFullScreen(true);

            primaryStage.setScene(Scene);
            primaryStage.show();
        }
    public static void main(String[] args) {
        launch(args);
    }
}
