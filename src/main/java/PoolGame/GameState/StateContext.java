package PoolGame.GameState;

public class StateContext {
    private State gameState;

    public StateContext(){
        gameState = null;
    }

    public StateContext(State gameState){
        this.gameState = gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public State getGameState() {
        return gameState;
    }
}
