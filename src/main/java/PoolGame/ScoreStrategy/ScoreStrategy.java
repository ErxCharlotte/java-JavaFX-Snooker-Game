package PoolGame.ScoreStrategy;

import PoolGame.Game;
import PoolGame.Items.Ball;

public interface ScoreStrategy {
    public int getScore(Game game, Ball ball);
}
