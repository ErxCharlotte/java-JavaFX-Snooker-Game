package PoolGame.GameState;

import PoolGame.ConfigReader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface State {
    public Scene handle(StateContext stateContext, Stage stage, double FRAMETIME);
    public ConfigReader loadLevelConfig();
}
