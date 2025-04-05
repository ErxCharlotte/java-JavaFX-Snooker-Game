package PoolGame.Cheat;

import PoolGame.Game;
import PoolGame.GameState.EasyState;
import PoolGame.GameState.StateContext;
import PoolGame.Items.Ball;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CheatBall {
    List<Ball> balls;
    ArrayList<String> colours = new ArrayList<String>();
    Game game;
    public HashMap<String, Integer> recoverPosition = new HashMap<String, Integer>();


    public CheatBall(Game game){
        this.game = game;
        this.balls = game.getPoolTable().getBalls();

        for(Ball ball: balls){
            String colourString = ball.getColour().toString();
            if(colours.contains(colourString) == false) {
                colours.add(colourString);
            }
        }
    }

    public void cheatBallByColour(String colour) {
        if (colours.size() <= 1){
            System.out.println("There are no color balls to cheat anymore.");
        }else {
            for (Ball ball : balls) {
                if (ball.getColour().toString().equals(colour)) {
                    ball.isCheat = true;
                    ball.disable();
                    game.addScore(ball.getScoreAction().getScore(game, ball));
                }
            }
        }
    }

    public void getCheatButton(Group root){
        CheatBall cheatBall = new CheatBall(game);
        for (int i = 0; i < colours.size(); i++){
            if (colours.get(i).equals("0xffffffff")){
                continue;
            }

            String colourString = colours.get(i);
            Button buttonColour = new Button("Cheat on this colour");
            buttonColour.setTextFill(Paint.valueOf("0xffffffff"));

            //position
            buttonColour.setLayoutX(game.getWindowDimX() + 14);
            buttonColour.setLayoutY(150 + i*30);
            recoverPosition.put(colours.get(i), 150 + i*30);

            //color
            BackgroundFill bgFill = new BackgroundFill(Color.web(colourString), null, null);
            Background bg = new Background(bgFill);
            buttonColour.setBackground(bg);

            root.getChildren().add(buttonColour);

            buttonColour.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    cheatBall.cheatBallByColour(colourString);
                    root.getChildren().remove(buttonColour);
                }
            });
        }
    }

    public void recoverCheatButton(Group root, String colour, int pos){
            String colourString = colour;
            Button buttonColour = new Button("Cheat on this colour");
            buttonColour.setTextFill(Paint.valueOf("0xffffffff"));

            Random rd = new Random();
            //position
            buttonColour.setLayoutX(game.getWindowDimX() + 14);
            buttonColour.setLayoutY(pos);

            //color
            BackgroundFill bgFill = new BackgroundFill(Color.web(colourString), null, null);
            Background bg = new Background(bgFill);
            buttonColour.setBackground(bg);

            root.getChildren().add(buttonColour);

            buttonColour.setOnMouseClicked(
                    (actionEvent) -> {
                        System.out.println(colourString);
                        this.cheatBallByColour(colourString);
                        root.getChildren().remove(buttonColour);
                    }
            );
    }
}
