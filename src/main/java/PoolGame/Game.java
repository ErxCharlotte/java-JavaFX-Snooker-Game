package PoolGame;

import java.util.ArrayList;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Cheat.CheatBall;
import PoolGame.Config.BallConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import PoolGame.Memento.Memento;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

/** The game class that runs the game */
public class Game {
    private PoolTable table;
    private boolean shownWonText = false;
    private final Text winText = new Text(50, 50, "Win and Bye");

    private String timeString;
    private int seconds = 0;
    private int minutes = 0;

    private int tickCount = 0;

    private int score = 0;

    private Memento memento;

    private Memento initMemento;

    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
    }

    private void setup(ConfigReader config) {
        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        this.table.setupBalls(balls);
        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 2);
        this.winText.setY(table.getDimY() / 2);

        this.initMemento = new Memento(this);
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);
    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
        this.seconds = 0;
        this.minutes = 0;
    }

    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
            this.tickCount = 0;
        }
        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }

        if(table.hasWon()== false) {
            if (tickCount % 60 == 0 && tickCount != 0) {
                if (this.seconds != 59) {
                    this.seconds += 1;
                } else if (this.seconds == 59) {
                    this.seconds = 0;
                    this.minutes += 1;
                }
            }

            tickCount++;
            if (this.seconds < 10) {
                this.timeString = String.valueOf(minutes) + ":0" + String.valueOf(seconds);
            } else {
                this.timeString = String.valueOf(minutes) + ":" + String.valueOf(seconds);
            }
        }
        this.memento = this.saveStateAction();
    }

    public String getTimeString() {
        return timeString;
    }
    public void addScore(int score){
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public boolean isShownWonText(){
        return this.shownWonText;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTickCount() {
        return tickCount;
    }

    public void setTable(PoolTable table) {
        this.table = table;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setShownWonText(boolean shownWonText) {
        this.shownWonText = shownWonText;
    }

    public void setTickCount(int tickCount) {
        this.tickCount = tickCount;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getScoreString() {
        return String.valueOf(this.score);
    }

    public Memento saveStateAction(){
       int cheatNum = 0;
       for (Ball ball: this.getPoolTable().getBalls()){
           if (ball.isStoreMemento() == true){
               this.memento = new Memento(this);
               ball.setIsStoreMemento(false);
           }

           if (ball.isCheat == true){
               cheatNum ++;
           }
       }
        for (Ball ball: this.getPoolTable().getBalls()) {
            if (ball.isCheat == true) {
                this.memento = new Memento(this);
                memento.setScore(this.score - ball.getScoreAction().getScore(this, ball)*cheatNum);
                ball.setIsStoreMemento(false);
            }
        }

       if (this.memento == null){
           return this.initMemento;
       }
       return this.memento;
    }

    public void undoAction(Group root, Memento memento, CheatBall cb){
        memento.getGameState(root,this, cb);
    }


}
