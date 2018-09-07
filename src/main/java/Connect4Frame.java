import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main driver of the Connect4Game, as well as the visualization of it.
 *
 * <p>You should not modify this class, and your agent should not need to access the methods within
 * it directly.</p>
 */
@SuppressWarnings("serial")
public class Connect4Frame extends JFrame {
  /**
   * The panel storing the visual of the game itself.
   */
  private Connect4Panel myPanel;
  /**
   * The game itself.
   */
  private Connect4Game myGame;
  /**
   * The two players playing the game.
   */
  private Agent redPlayer, yellowPlayer;
  /**
   * Booleans controlling whose turn it is and whether a game is ongoing.
   */
  private boolean redPlayerturn, gameActive;
  /**
   * The buttons controlling the game.
   */
  private JButton newGameButton, nextMoveButton, playToEndButton;
  /**
   * The status label describing the events of the game.
   */
  private JLabel updateLabel;
  /**
   * A random number generator to randomly decide who plays first.
   */
  private Random r;

  /**
   * Creates a new Connect4Frame with a given game and pair of players.
   *
   * <p>Your agent will not need to use this method.</p>
   *
   * @param game the game itself.
   * @param redPlayer the agent playing as the red tokens.
   * @param yellowPlayer the agent playing as the yellow tokens.
   */
  public Connect4Frame(Connect4Game game, Agent redPlayer, Agent yellowPlayer) {
    super();

    this.myGame = game;   // stores the game itself
    this.redPlayer = redPlayer;   // stores the red player
    this.yellowPlayer = yellowPlayer; // stores the yellow player
    gameActive = false;   // initially sets that no game is active
    r = new Random();   // creates the random number generator

    myPanel = new Connect4Panel(game);  // creates the panel for displaying the game

    newGameButton = new JButton("Start a New Game");    // creates the button for starting a new game
    newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);    // center-aligns the new game button
    newGameButton.addActionListener(new ActionListener() {  // connects the new game button to its buttonPressed method
      public void actionPerformed(ActionEvent e) {
        newGameButtonPressed();
      }
    });

    nextMoveButton = new JButton("Play Next Move"); // creates the button for playing the next move
    nextMoveButton.setEnabled(false);   // disables the button until a game is started
    nextMoveButton.setAlignmentX(Component.CENTER_ALIGNMENT);   // centers the button
    nextMoveButton.addActionListener(new ActionListener() { // connects the play next move button to its buttonPressed method
      public void actionPerformed(ActionEvent e) {
        nextMoveButtonPressed();
      }
    });

    playToEndButton = new JButton("Play to End");   // creates the button for finishing the game
    playToEndButton.setEnabled(false);  // disables the button until a game is started
    playToEndButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // centers the button
    // connects the play to end button to its buttonPressed method
    playToEndButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        playToEndButtonPressed();
      }
    });

    updateLabel = new JLabel(redPlayer.toString() + " vs. " + yellowPlayer.toString()); // creates the status label
    updateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // centers the status label

    JPanel buttonPane = new JPanel();   // creates a pane for the buttons
    buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));  // sets the button pane to be horizontally oriented
    // adding and spacing out the buttons
    buttonPane.add(Box.createHorizontalGlue());
    buttonPane.add(newGameButton);
    buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
    buttonPane.add(nextMoveButton);
    buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
    buttonPane.add(playToEndButton);
    buttonPane.add(Box.createHorizontalGlue());

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));    // sets the overall pane to be vertically oriented
    this.add(buttonPane);   // adds the pane containing the buttons
    this.add(updateLabel);  // adds the update label
    this.add(myPanel);  // adds the visual of the game board
    this.pack();    // shrinks the window to the appropriate size
    this.setResizable(false);   // makes the window not resizable
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // close the application when the window is closed
    this.setVisible(true);  // show the window
  }

  /**
   * Changes the text of the update label.
   *
   * <p>Your agent will not need to use this method.</p>
   *
   * @param text the next text for the update label.
   */
  public void alert(String text) {
    updateLabel.setText(text);
    this.repaint();
  }

  /**
   * Runs the next move of the game.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  private void nextMove() {
    Connect4Game oldBoard = new Connect4Game(myGame);   // store the old board for validation
    // Run the appropriate player's move
    if (redPlayerturn) {
      redPlayer.move();
      alert(yellowPlayer.toString() + " plays next...");
    } else {
      yellowPlayer.move();
      alert(redPlayer.toString() + " plays next...");
    }
    // check and make sure this is a valid next move for this board
    String validateResult = oldBoard.validate(myGame);
    // if there was a validation error, show it and cancel the game
    if (validateResult.length() > 0) {
      alert(validateResult);  // show the error
      disableButtons();   // stop the game
      gameActive = false;
    }
    redPlayerturn = !redPlayerturn;   // switch whose turn it is
    char won = myGame.gameWon();    // check if the game has been won
    // if the game has been won...
    if (won != 'N') {
      disableButtons();   // disable the buttons
      gameActive = false;
      // if a player won, say so
      if (myGame.gameWon() == 'R') {
        alert(redPlayer.toString() + " wins!");
      } else if (myGame.gameWon() == 'Y') {
        alert(yellowPlayer.toString() + " wins!");
      }
    } else if (myGame.boardFull()) {
      disableButtons();   // disable the buttons
      alert("The game ended in a draw!"); // announce the draw
      gameActive = false;
    }
    this.repaint();
  }

  /**
   * Clear the board and start a new game.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  private void newGame() {
    myGame.clearBoard();
    enableButtons();
    gameActive = true;
    redPlayerturn = r.nextBoolean();
    if (redPlayerturn) {
      alert(redPlayer.toString() + " plays first!");
      myGame.setRedPlayedFirst(true);
    } else {
      alert(yellowPlayer.toString() + " plays first!");
      myGame.setRedPlayedFirst(false);
    }
    this.repaint();
  }

  /**
   * Runs the game until it's over.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  private void playToEnd() {
    // keep playing the next move until the game ends
    while (gameActive) {
      nextMove();
    }
    char won = myGame.gameWon();
    // when it ends, announce how it ended: win or draw
    if (won != 'N') {
      disableButtons();
      if (myGame.gameWon() == 'R') {
        alert(redPlayer.toString() + " wins!");
      } else if (myGame.gameWon() == 'Y') {
        alert(yellowPlayer.toString() + " wins!");
      }
    } else if (myGame.boardFull()) {
      disableButtons();
      alert("The game ended in a draw!");
    } else {
   // if it didn't end in a win or draw, leave the error message up
      disableButtons();
    }
  }

  /**
   * Reacts to the new game button being pressed.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  public void newGameButtonPressed() {
    newGame();
  }

  /**
   * Reacts to the next move button being pressed.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  public void nextMoveButtonPressed() {
    nextMove();
  }

  /**
   * Reacts to the play to end button being pressed.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  public void playToEndButtonPressed() {
    playToEnd();
  }

  /**
   * Disables the buttons.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  private void disableButtons() {
    nextMoveButton.setEnabled(false);
    playToEndButton.setEnabled(false);
  }

  /**
   * Enables the buttons.
   *
   * <p>Your agent will not need to use this method.</p>
   */
  private void enableButtons() {
    nextMoveButton.setEnabled(true);
    playToEndButton.setEnabled(true);
  }

}
