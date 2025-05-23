package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.ScoreStrategy.ScoreStrategy;
import PoolGame.Strategy.BallPocketStrategy;

public class BlackBallBuilder implements BallBuilder {

    private Ball ball;
    private Ball.BallType ballType = null;
    private BallPocketStrategy action = null;

    private ScoreStrategy scoreAction = null;

    public BlackBallBuilder() {
        this.reset();
    }

    public BlackBallBuilder(Ball.BallType type, BallPocketStrategy action, ScoreStrategy scoreAction) {
        this.ballType = type;
        this.action = action;
        this.scoreAction = scoreAction;
        this.reset();
    }

    public void reset() {
        this.ball = new Ball();
        this.ball.setColour("black");
        if (ballType != null) {
            this.ball.setBallType(this.ballType);
        }
        if (this.action != null) {
            this.ball.setPocketAction(this.action);
        }
        if (this.scoreAction != null) {
            this.ball.setScoreAction(this.scoreAction);
        }
    }

    public void setXPos(double xPos) {
        this.ball.setInitialXPos(xPos);
    }

    public void setYPos(double yPos) {
        this.ball.setInitialYPos(yPos);
    }

    public void setXVel(double xVel) {
        this.ball.setInitialXVel(xVel);
    }

    public void setYVel(double yVel) {
        this.ball.setInitialYVel(yVel);
    }

    public void setMass(double mass) {
        this.ball.setMass(mass);
    }

    public void setBallType(Ball.BallType type) {
        this.ballType = type;
        this.ball.setBallType(type);
    }

    public void setPocketAction(BallPocketStrategy action) {
        this.action = action;
        this.ball.setPocketAction(action);
    }

    public Ball finaliseBuild() {
        Ball ball = this.ball;
        this.reset();
        return ball;
    }

    public void setScoreAction(ScoreStrategy scoreAction) {
        this.scoreAction = scoreAction;
        this.ball.setScoreAction(scoreAction);
    }
}
