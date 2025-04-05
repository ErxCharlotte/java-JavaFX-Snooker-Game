SID: 510012005

Application Brief:
This is a snooker game, and players can complete most of the billiard game content (such as hitting the ball, ball collision, etc.)
in the app. And players can undo or cheat according to their own preferences.



How to run the code:
First enter "cd path" in the command line to move to the current path of the game.
Next, enter "gradle build" to build the game. Finally, enter "gradle run" to play the game.

By default, the default level after entering "gradle run" is "easy" level,
and you can adjust the difficulty via buttons in the game interface.
Or enter gradle run --args="'insert_config_file_path'" to customize the difficulty level of the game.

(Please make sure that the format and quantity in the JSON file for each configuration are correct, otherwise the app may experience errors.)

Among them, since the size of the window of each computer is different,
the "magic numbers" in my current code are 14 and 26.
If this number does not match the size of your computer's window,
you can use the APP class in the Make changes in lines 93, 96
(Also change the numbers in each "level" State class, otherwise the window may appear strange.)



Features have implemented:
1. Players can now use more colored balls (can be set in JSON file)
2. Players can now customize the difficulty of the game (the default is "Easy"), the current difficulties are: "Easy", "Normal", "Hard".
3. The game interface can now display the time and score.
4. When the player clicks the cue ball, the animation becomes more realistic.
5. The player can now undo the previous operation and go back to the previous step
6. Players can cheat and eliminate all balls of any color with one click.



How to use the new features:
1. The position, color and pocket position of the ball can be modified in the JSON file.
2.By clicking the "Easy", "Normal" and "Hard" buttons on the game interface, you can realize the transition of different game levels, and there are texts below the buttons to remind you what level you are currently in.
3. By clicking "cheat on this colour" in the game interface, you can eliminate the balls of that color currently in the game (for example, if you click red, all red balls will be eliminated).
4. You can undo the operation just now by clicking "Recover to last state" in the game interface. For example, if you cheated the red ball just now, when you choose to recover, the red ball will return to the desktop,
and the score and time will also be displayed to that moment just now.



Design patterns:
1.State Pattern:
Context: GameState - StateContext.java
State: GameState - State.java
ConcreteStateA: GameState - HardState.java
ConcreteStateB: GameState - NormalState.java
ConcreteStateC: GameState - EasyState.java

2.Memento Design Pattern:
Memento: Memento - Memento.java
Caretaker: Memento - Caretaker.java
Originator: Game.java

3.Strategy Design Pattern:
Strategy: ScoreStrategy - ScoreStrategy.java
Context: Builder - BallBuilderDirector.java
ConcreteStrategyA: BlackBallScore
ConcreteStrategyB: BlueBallScore
ConcreteStrategyC: BrownBallScore
ConcreteStrategyD: GreenBallScore
ConcreteStrategyE: OrangeBallScore
ConcreteStrategyF: PurpleBallScore
ConcreteStrategyG: RedBallScore
ConcreteStrategyH: WhiteBallScore
ConcreteStrategyI: YellowBallScore
