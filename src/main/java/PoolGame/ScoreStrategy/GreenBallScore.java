package PoolGame.ScoreStrategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

public class GreenBallScore implements ScoreStrategy {
    @Override
    public int getScore(Game game, Ball ball) {
        return 3;
    }
}