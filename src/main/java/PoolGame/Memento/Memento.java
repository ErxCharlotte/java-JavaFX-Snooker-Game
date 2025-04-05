package PoolGame.Memento;

import PoolGame.Cheat.CheatBall;
import PoolGame.Game;
import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Memento {
    private PoolTable table;
    private boolean shownWonText;

    private String timeString;
    private int seconds;
    private int minutes;

    private int tickCount;

    private int score;

    private List<Ball> balls;
    private HashMap<Ball, List<Double>> storedMap = new HashMap<Ball, List<Double>>();
    private HashMap<Ball, Boolean> isCheatMap = new HashMap<Ball, Boolean>();

    public Memento(){
    }

    public Memento(Game game){
        this.table = game.getPoolTable();
        this.shownWonText = game.isShownWonText();
        this.timeString = game.getTimeString();
        this.seconds = game.getSeconds();
        this.minutes = game.getMinutes();
        this.tickCount = game.getTickCount();
        this.score = game.getScore();
        this.balls = this.table.getBalls();

        for (Ball ball: balls){
            List<Double> ls = new ArrayList<>();
            ls.add(ball.getXPos());
            ls.add(ball.getXVel());
            ls.add(ball.getYPos());
            ls.add(ball.getYVel());
            storedMap.put(ball, ls);

            if (ball.isCheat) {
                isCheatMap.put(ball, true);
            }else{
                isCheatMap.put(ball, false);
            }
            ball.isCheat = false;
        }

    }

    public void getGameState(Group root, Game game, CheatBall cb) {
        game.setTable(this.table);
        game.setShownWonText(this.shownWonText);
        game.setTimeString(this.timeString);
        game.setSeconds(this.seconds);
        game.setMinutes(this.minutes);
        game.setTickCount(this.tickCount);
        game.setScore(this.score);

        String colourString = "";
        for (Ball ball: this.balls){
            List ls = storedMap.get(ball);
            ball.setXPos((Double) ls.get(0));
            ball.setXVel(0);
            ball.setYPos((Double) ls.get(2));
            ball.setYVel(0);
            if (isCheatMap.get(ball)){
                colourString=ball.getColour().toString();
                ball.unDisable();
                isCheatMap.put(ball, false);
                ball.isCheat = false;
            }
        }
        if (colourString != "") {
            int position = cb.recoverPosition.get(colourString);
            cb.recoverCheatButton(root, colourString, position);
        }
    }

    public void setGameState(Game game) {
        this.table = game.getPoolTable();
        this.shownWonText = game.isShownWonText();
        this.timeString = game.getTimeString();
        this.seconds = game.getSeconds();
        this.minutes = game.getMinutes();
        this.tickCount = game.getTickCount();
        this.score = game.getScore();
        this.balls = this.table.getBalls();

        for (Ball ball: balls){
            List<Double> ls = new ArrayList<>();
            ls.add(ball.getXPos());
            ls.add(ball.getXVel());
            ls.add(ball.getYPos());
            ls.add(ball.getYVel());
            storedMap.put(ball, ls);

            if (ball.isCheat) {
                isCheatMap.put(ball, true);
            }else{
                isCheatMap.put(ball, false);
            }
            ball.isCheat = false;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }
}
