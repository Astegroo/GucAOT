package game.GUI;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class EasyController {
    public Label HealthWall1;
    public Label DangerLevelWall1;
    public Label HealthWall2;
    public Label DangerLevelWall2;
    public Label HealthWall3;
    public Label DangerLevelWall3;
    public RadioButton RadioLane1;
    public RadioButton RadioLane2;
    public RadioButton RadioLane3;
    public VBox VBoxLane1;
    public VBox VBoxLane2;
    public VBox VBoxLane3;
    @FXML
    private Label score;
    @FXML
    private Label turns;

    @FXML
    public Stage stage;
    @FXML
    public Scene scene;
    @FXML
    public Label resourcesLabel;
    @FXML
    private Label phase;
    @FXML
    private GridPane LaneGridPane;
    public Battle battle;

    public void initialize(){
        try {
            battle = new Battle(0,0,9,3,250);
        } catch (IOException e) {
            errorMessage("Couldn't Initialize Game");

        }

        ToggleGroup t = new ToggleGroup();
        RadioLane1.setToggleGroup(t);
        RadioLane2.setToggleGroup(t);
        RadioLane3.setToggleGroup(t);

    }


    public void Turn() {
        //Pass Turn
        try {
            battle.passTurn();
        } catch (IOException e) {
            errorMessage("Couldn't Pass Turn");

        }
        //Check for end
        if(battle.isGameOver()){
            errorMessage("Game Over! You have lost! Your Score is " + battle.getScore());
            BackToMenu();
        }
        //Change stats
        resourcesLabel.setText((String.valueOf(battle.getResourcesGathered())));
        turns.setText(String.valueOf(battle.getNumberOfTurns()));
        phase.setText(String.valueOf(battle.getBattlePhase()));
        score.setText(String.valueOf(battle.getScore()));
        //Clear Lanes Before updating them all
        clearLanes();
        updateLanes(1);
        updateLanes(2);
        updateLanes(3);
        //Update Walls
        HealthWall1.setText("Health: " + String.valueOf(battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()));
        HealthWall2.setText("Health: " + String.valueOf(battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()));
        HealthWall3.setText("Health: " + String.valueOf(battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()));
        DangerLevelWall1.setText("Danger Level: " + String.valueOf(battle.getOriginalLanes().get(0).getDangerLevel()));
        DangerLevelWall2.setText("Danger Level: " + String.valueOf(battle.getOriginalLanes().get(1).getDangerLevel()));
        DangerLevelWall3.setText("Danger Level: " + String.valueOf(battle.getOriginalLanes().get(2).getDangerLevel()));
        if(battle.getOriginalLanes().get(0).getLaneWall().getCurrentHealth() <= 0){
            HealthWall1.setText("Lane Lost");
        }
        if(battle.getOriginalLanes().get(1).getLaneWall().getCurrentHealth() <= 0){
            HealthWall2.setText("Lane Lost");
        }
        if(battle.getOriginalLanes().get(2).getLaneWall().getCurrentHealth() <= 0){
            HealthWall3.setText("Lane Lost");
        }

    }

    public void updateLanes(int i){
        Lane l = battle.getOriginalLanes().get(i-1);

        for(Titan t :l.getTitans()) {
            if(t.isDefeated()){
                l.getTitans().remove(t);
            }
            ImageView Image = new ImageView();
            Text text = new Text(String.valueOf(t.getCurrentHealth()));
            text.setFont(new Font(15));

            StackPane stack = new StackPane();

            Image.setOnMouseEntered(event ->{
                ProgressBar pb = new ProgressBar();
            });

            Image.setOnMouseExited(mouseEvent -> {

            });

            if(t instanceof PureTitan){
                Image img = new Image(getClass().getResourceAsStream("pure.png"));
                Image.setImage(img);
                Image.setFitWidth(57);
                Image.setFitHeight(t.getHeightInMeters()*2);
                stack.getChildren().addAll(Image,text);
            }else if(t instanceof  AbnormalTitan){
                Image img = new Image(getClass().getResourceAsStream("abnormal.jpg"));
                Image.setImage(img);
                Image.setFitWidth(57);
                Image.setFitHeight(t.getHeightInMeters()*2);
                stack.getChildren().addAll(Image,text);
            }else if(t instanceof ArmoredTitan){
                Image img = new Image(getClass().getResourceAsStream("armored.png"));
                Image.setImage(img);
                Image.setFitWidth(57);
                Image.setFitHeight(t.getHeightInMeters()*2);
                stack.getChildren().addAll(Image,text);

            }else{
                Image img = new Image(getClass().getResourceAsStream("colossal.png"));
                Image.setImage(img);
                Image.setFitWidth(57);
                Image.setFitHeight(t.getHeightInMeters()*2);
                stack.getChildren().addAll(Image,text);
            }
            LaneGridPane.add(stack,t.getDistance(), i-1);
        }

    }

    public void clearLanes(){
        LaneGridPane.getChildren().clear(); //remove all titans before updating gridpane.

    }

    public void purchaseWeaponInLane(int weaponCode){
        if(RadioLane1.isSelected()){
            try {
                battle.purchaseWeapon(weaponCode,battle.getOriginalLanes().get(0));
                ImageView imgView = new ImageView();
                Image img = new Image(getClass().getResourceAsStream(getImageForWeapon(weaponCode)));
                imgView.setImage(img);
                imgView.setFitHeight(50);
                imgView.setFitWidth(50);
                VBoxLane1.getChildren().add(imgView);
                Turn();

            } catch (InsufficientResourcesException | InvalidLaneException | IOException e) {
                errorMessage("Not enough Resources or Invalid Lane!");
            }
        }
        if(RadioLane2.isSelected()){
            try {
                battle.purchaseWeapon(weaponCode,battle.getOriginalLanes().get(1));
                ImageView imgView = new ImageView();
                Image img = new Image(getClass().getResourceAsStream(getImageForWeapon(weaponCode)));
                imgView.setImage(img);
                imgView.setFitHeight(50);
                imgView.setFitWidth(50);
                VBoxLane2.getChildren().add(imgView);
                Turn();

            } catch (InsufficientResourcesException | InvalidLaneException | IOException e) {
                errorMessage("Not enough Resources or Invalid Lane!");
            }
        }
        if(RadioLane3.isSelected()){
            try {
                battle.purchaseWeapon(weaponCode,battle.getOriginalLanes().get(2));
                ImageView imgView = new ImageView();
                Image img = new Image(getClass().getResourceAsStream(getImageForWeapon(weaponCode)));
                imgView.setImage(img);
                imgView.setFitHeight(50);
                imgView.setFitWidth(50);
                VBoxLane3.getChildren().add(imgView);
                Turn();

            } catch (InsufficientResourcesException | InvalidLaneException | IOException e) {
                errorMessage("Not enough Resources or Invalid Lane!");
            }
        }
    }

    public String getImageForWeapon(int weaponCode){
        if(weaponCode == 1){
            return("PC.png");
        }
        else if(weaponCode == 2){
            return("SC.jpg");
        }
        else if(weaponCode == 3){
            return("VSC.png");
        }
        else{
            return("WT.png");
        }
    }

    public void purchasePiercingCannon(){
        purchaseWeaponInLane(1);
    }
    public void purchaseSniperCannon(){
        purchaseWeaponInLane(2);
    }
    public void purchaseVolleySpreadCannon(){
        purchaseWeaponInLane(3);
    }
    public void purchaseWallTrap(){
        purchaseWeaponInLane(4);
    }

    public void errorMessage(String msg){
        Alert error = new Alert(Alert.AlertType.ERROR);

        error.setHeaderText("Something Wrong Happened!");
        error.setContentText(msg);
        error.showAndWait();

    }

    public void BackToMenu(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        } catch (IOException e) {
            errorMessage("Something wrong has happened!");
        }
        stage = (Stage) score.getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("menustyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}


