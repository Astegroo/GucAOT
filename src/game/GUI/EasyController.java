package game.GUI;

import game.engine.Battle;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EasyController {
    @FXML
    private Label score;
    @FXML
    private Label turns;
    @FXML
    public Label resourcesLabel;
    @FXML
    private Label phase;
    @FXML
    public Stage stage;
    public Scene scene;
    @FXML
    private GridPane WallGridPane;
    @FXML
    private GridPane LaneGridPane;
    public Battle battle;

    public void initialize() throws IOException {
         battle = new Battle(0,0,9,3,250);

    }

    public void SwitchToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        scene = new Scene(root);
        stage = (Stage) score.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void Turn() throws IOException {
        System.out.print(resourcesLabel+"");
        System.out.println(score);
        battle.passTurn();

        if(battle.isGameOver()){
            SwitchToMenu();
        }


        resourcesLabel.setText((String.valueOf(battle.getResourcesGathered())));
        turns.setText(String.valueOf(battle.getNumberOfTurns()));
        phase.setText(String.valueOf(battle.getBattlePhase()));
        score.setText(String.valueOf(battle.getScore()));

        clearLanes();
        updateLanes(1);
        updateLanes(2);
        updateLanes(3);


    }

    public void updateLanes(int i){
        Lane l = battle.getOriginalLanes().get(i-1);

        for(Titan t :l.getTitans()) {

            ImageView Image = new ImageView();
            if(t instanceof PureTitan){
                Image img = new Image(getClass().getResourceAsStream("pure.png"));
                Image.setImage(img);
                Image.setFitWidth(33);
                Image.setFitHeight(50);
            }else if(t instanceof  AbnormalTitan){
                Image img = new Image(getClass().getResourceAsStream("abnormal.jpg"));
                Image.setImage(img);
                Image.setFitWidth(33);
                Image.setFitHeight(70);
            }else if(t instanceof ArmoredTitan){
                Image img = new Image(getClass().getResourceAsStream("armored.png"));
                Image.setImage(img);
                Image.setFitWidth(33);
                Image.setFitHeight(90);
            }else{
                Image img = new Image(getClass().getResourceAsStream("colossal.png"));
                Image.setImage(img);
                Image.setFitWidth(33);
                Image.setFitHeight(110);
            }

            LaneGridPane.add(Image,t.getDistance(), i-1);
        }
//        for(int i = 0; i<battle.getLanes().size();i++){
//            for(int j = 0; j<battle.getLanes().poll().getTitans().size();j++){
//                if(battle.getLanes().poll().getTitans().peek() instanceof AbnormalTitan){
//                    ImageView image = new ImageView("colossal.png");
//                    LaneGridPane.add(image,i,10-battle.getLanes().peek().getTitans().poll().getDistance());
//                    // fokek mel method dee its an extremely failed attempt at filling the grid
    }

    public void clearLanes(){
        LaneGridPane.getChildren().clear(); //remove all titans before updating gridpane.

    }
}


