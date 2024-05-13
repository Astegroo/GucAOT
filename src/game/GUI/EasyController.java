package game.GUI;

import game.engine.Battle;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EasyController {
    public Label score;
    public Label turns;
    public Label resources;
    public Label phase;
    public Stage stage;
    public Scene scene;
    public GridPane WallGridPane;
    public GridPane LaneGridPane;


    public void initialize() throws IOException {
        Battle EasyBattle = new Battle(0,0,10,3,250);

    }

    public void SwitchToMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Turn(Battle battle) throws IOException {
        if(battle.isGameOver()){
            SwitchToMenu();
        }

        resources.setText((String.valueOf(battle.getResourcesGathered())));
        turns.setText(String.valueOf(battle.getNumberOfTurns()));
        phase.setText(String.valueOf(battle.getBattlePhase()));
        score.setText(String.valueOf(battle.getScore()));

        battle.passTurn();
    }

    public void UpdateLanes(Battle battle){
        LaneGridPane.getChildren().clear(); //remove all titans before updating gridpane.

        for(int i = 0; i<battle.getLanes().size();i++){
            for(int j = 0; j<battle.getLanes().poll().getTitans().size();j++){
                if(battle.getLanes().poll().getTitans().peek() instanceof AbnormalTitan){
                    ImageView image = new ImageView("abnormal.png");
                    LaneGridPane.add(image,i,10-battle.getLanes().peek().getTitans().poll().getDistance());
                    // fokek mel method dee its an extremely failed attempt at filling the grid
                }
            }

        }
    }

}
