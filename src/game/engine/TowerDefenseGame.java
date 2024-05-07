package game.engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TowerDefenseGame extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_LANES = 3; // Number of lanes in the game area
    private static final int LANE_HEIGHT = HEIGHT / NUM_LANES;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tower Defense Game");

        // Create the root node
        BorderPane root = new BorderPane();

        // Create the game area
        Pane gameArea = new Pane();
        gameArea.setPrefSize(WIDTH, HEIGHT);

        // Create lanes
        for (int i = 0; i < NUM_LANES; i++) {
            Rectangle lane = new Rectangle(0, i * LANE_HEIGHT, WIDTH, LANE_HEIGHT);
            lane.setFill(Color.LIGHTGRAY);
            gameArea.getChildren().add(lane);
        }

        // Create the control area
        GridPane controlArea = new GridPane();
        controlArea.setPrefWidth(WIDTH);
        controlArea.setPrefHeight(100); // Adjust height as needed

        // Add game area and control area to the root
        root.setCenter(gameArea);
        root.setBottom(controlArea);

        // Create the scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}