package game.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class TowerDefenseGame extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_LANES = 3; // Number of lanes in the game area
    private static final int LANE_HEIGHT = HEIGHT / NUM_LANES;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setFullScreen(true);
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.show();
        Parent r = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene Scene2 = new Scene(r);
        primaryStage.setScene(Scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}