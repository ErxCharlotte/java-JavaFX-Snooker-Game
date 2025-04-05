package PoolGame.GameState;

import PoolGame.Cheat.CheatBall;
import PoolGame.ConfigReader;
import PoolGame.Game;
import PoolGame.Items.PoolTable;
import PoolGame.Memento.Caretaker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HardState implements State {

    @Override
    public Scene handle(StateContext stateContext, Stage stage, double FRAMETIME) {
        Group root = new Group();
        Scene scene = new Scene(root);
        Caretaker ct = new Caretaker();

        stage.setScene(scene);
        stage.setTitle("PoolGame");
        stage.show();

        ConfigReader config = this.loadLevelConfig();
        Game game = new Game(config);

        Canvas canvas = new Canvas(game.getWindowDimX(), game.getWindowDimY());

        stage.setWidth(game.getWindowDimX() + 14 + game.getWindowDimX()/3);
        stage.setHeight(game.getWindowDimY() +
                game.getPoolTable().POCKET_OFFSET +
                PoolTable.POCKET_OFFSET + 26); // Magic number to get bottom to align
        //For placing timers, scorers, level switches

        root.getChildren().add(canvas);
        // GraphicsContext gc = canvas.getGraphicsContext2D();
        game.addDrawables(root);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        //Time
        Label timeCounter = new Label();
        timeCounter.setLayoutX(game.getWindowDimX() + 14);
        timeCounter.setLayoutY(100);
        root.getChildren().add(timeCounter);

        //Score
        Label scoreCounter = new Label();
        scoreCounter.setLayoutX(game.getWindowDimX() + 14);
        scoreCounter.setLayoutY(150);
        root.getChildren().add(scoreCounter);

        KeyFrame frame = new KeyFrame(Duration.seconds(FRAMETIME),
                (actionEvent) -> {
                    game.tick();
                    timeCounter.setText("Time: " + game.getTimeString());
                    scoreCounter.setText("Score: " + game.getScoreString());
                });


        timeline.getKeyFrames().add(frame);
        timeline.play();

        //button for cheat
        CheatBall cheatBall = new CheatBall(game);
        cheatBall.getCheatButton(root);

        //button for levels
        Button buttonEasy = new Button("Easy");
        Button buttonNormal = new Button("Normal");
        Button buttonHard = new Button("Hard");

        buttonEasy.setLayoutX(game.getWindowDimX() + 14);
        buttonEasy.setLayoutY(25);
        buttonNormal.setLayoutX(game.getWindowDimX() + 64);
        buttonNormal.setLayoutY(25);
        buttonHard.setLayoutX(game.getWindowDimX() + 129);
        buttonHard.setLayoutY(25);

        Text t = new Text(game.getWindowDimX() + 14, 15, "Select the level please.");
        root.getChildren().add(buttonEasy);
        root.getChildren().add(buttonNormal);
        root.getChildren().add(buttonHard);
        root.getChildren().add(t);

        Text levelText = new Text(game.getWindowDimX() + 14, 65, "You are now at level: Hard.");
        root.getChildren().add(levelText);

        buttonEasy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Easy");

                StateContext stateContext = new StateContext();
                EasyState easyState = new EasyState();
                stage.setScene(easyState.handle(stateContext, stage, FRAMETIME));
            }
        });
        buttonNormal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Normal");

                StateContext stateContext = new StateContext();
                NormalState normalState = new NormalState();
                stage.setScene(normalState.handle(stateContext, stage, FRAMETIME));
            }
        });
        buttonHard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hard");

                StateContext stateContext = new StateContext();
                HardState hardState = new HardState();
                stage.setScene(hardState.handle(stateContext, stage, FRAMETIME));
            }
        });

        //Recover
        Button buttonRecover = new Button("Recover to the last state");
        buttonRecover.setLayoutX(game.getWindowDimX() + 14);
        buttonRecover.setLayoutY(game.getWindowDimY()-50);

        root.getChildren().add(buttonRecover);


        ct.setMemento(game.saveStateAction());
        buttonRecover.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ct.setMemento(game.saveStateAction());
                game.undoAction(root, ct.getMemento(), cheatBall);
            }
        });


        stateContext.setGameState(this);
        return scene;
    }

    public ConfigReader loadLevelConfig(){
        String configPath = "/config_hard.json";
        boolean isResourcesDir = true;

        // parse the file:
        ConfigReader config = null;
        try {
            config = new ConfigReader(configPath, isResourcesDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (ConfigReader.ConfigKeyMissingException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.printf("ERROR: %s\n", e.getMessage());
            System.exit(1);
        }
        return config;
    }
}