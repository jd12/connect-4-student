import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;

/**
 * The panel for displaying the current status of the game itself.
 *
 * <p>You should not modify this class, and your agent should not need to access the methods within
 * it directly.</p>
 */
@SuppressWarnings("serial")
public class Connect4Panel extends JPanel {
    /**
     * The game to display.
     */
    private Connect4Game myGame;
    /**
     * Size of the individual slots.
     */
    private final int slotDiameter = 75;
    /**
     * Space between slots.
     */
    private final int slotSpacing = 10;

    /**
     * Creates a new Connect4Panel with a given game.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param game the game to display.
     */
    public Connect4Panel(Connect4Game game) {
        super();
        this.myGame = game;
        final int WIDTH = 605;
        final int HEIGHT = 520;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Paints the current status of the game.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param g the graphics object with which to paint.
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLUE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < myGame.getColumnCount(); i++) {
            for (int j = 0; j < myGame.getRowCount(); j++) {
                Connect4Column column = myGame.getColumn(i);
                Connect4Slot currentSlot = column.getSlot(j);
                Color color;
                if (!currentSlot.getIsFilled()) {
                    color = Color.WHITE;
                } else {
                    if (currentSlot.getIsRed()) {
                        color = Color.RED;
                    } else {
                        color = Color.YELLOW;
                    }
                }
                int x = ((i + 1) * slotSpacing) + (i * slotDiameter);
                int y = ((j + 1) * slotSpacing) + (j * slotDiameter);
                drawSlot(g2, x, y, color);
                if (currentSlot.getIsHighlighted()) {
                    drawHighlight(g2, x, y);
                }
            }
        }
    }

    /**
     * Draw a single slot.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param g2 the graphics object with which to paint.
     * @param x the top-left x-coordinate where to draw the slot.
     * @param y the top-left y-coordinate where to draw the slot.
     * @param color the color for the slot.
     */
    public void drawSlot(Graphics2D g2, int x, int y, Color color) {
        g2.setColor(color);
        g2.fillOval(x, y, slotDiameter, slotDiameter);
    }
    /**
     * Highlight a slot.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param g2 the graphics object with which to paint.
     * @param x the top-left x-coordinate where to draw the highlight.
     * @param y the top-left y-coordinate where to draw the highlight.
     */
    public void drawHighlight(Graphics2D g2, int x, int y) {
        Stroke save = g2.getStroke();
        g2.setStroke(new BasicStroke(10.0f));
        g2.setColor(Color.GREEN);
        g2.drawOval(x + 4, y + 4, slotDiameter - 8, slotDiameter - 8);
        g2.setStroke(save);
    }
}
