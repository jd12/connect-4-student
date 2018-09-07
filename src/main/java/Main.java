/**
 * The main driver of the program. This file will create the game, create the two agents,
 * and create the window for the game. After that, Connect4Frame runs everything.
 *
 * <p>You should only modify this class to change which agents are playing.
 * Your options are:</p>
 * <ul>
 * <li>RandomAgent</li>
 * <li>BeginnerAgent</li>
 * <li>IntermediateAgent</li>
 * <li>AdvancedAgent</li>
 * <li>BrilliantAgent</li>
 * </ul>
 */
public class Main {
    /**
     * Main method that initializes the game.
     * @param args
     *      command line arguments
     */
    public static void main(String[] args) {
        // create the game; these sizes can be altered for larger or smaller games
        Connect4Game game = new Connect4Game(7, 6);
        // create the red player, any subclass of Agent
        Agent redPlayer = new RandomAgent(game, true);
        // create the yellow player, any subclass of Agent
        Agent yellowPlayer = new BeginnerAgent(game, false);
        // create the game window
        @SuppressWarnings("unused")
        Connect4Frame mainframe = new Connect4Frame(game, redPlayer, yellowPlayer);
    }
}
