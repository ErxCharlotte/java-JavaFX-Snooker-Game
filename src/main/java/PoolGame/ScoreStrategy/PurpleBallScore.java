package PoolGame.ScoreStrategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

public class PurpleBallScore implements ScoreStrategy {
    @Override
    public int getScore(Game game, Ball ball) {
        return 6;
    }
}