/**
 * A single slot in a Connect 4 board. A slot can be either empty or filled, and it can
 * be filled with either a red token or a yellow token.
 *
 * <p>You should not modify this class, but you will need to use methods within it.</p>
 */
public class Connect4Slot {

    /**
     * Reports whether the slot is filled.
     */
    private boolean isFilled;
    /**
     * Reports whether the slot is red.
     */
    private boolean isRed;
    /**
     * Reports whether the slot is highlighted.
     */
    private boolean isHighlighted;

    /**
     * Creates a new Connect4Slot, initially unfilled.
     *
     * <p>Your agent will not need to use this method.</p>
     */
    public Connect4Slot() {
        this.isFilled = false;
        this.isRed = false;
    }
    /**
     * Copies the given slot.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param slot the slot to copy.
     */
    public Connect4Slot(Connect4Slot slot) {
        this.isFilled = slot.getIsFilled();
        this.isRed = slot.getIsRed();
    }
    /**
     * Checks if the slot is currently filled.
     *
     * <p>Your agent WILL need to use this method.</p>
     *
     * @return true if filled, false if not.
     */
    public boolean getIsFilled() {
        return isFilled;
    }
    /**
     * If the slot is filled, checks if the token in the slot is red.
     *
     * <p>If the slot is not filled, this will still return false; so, this should only
     * be checked after checking getIsFilled().</p>
     *
     * <p>Your agent WILL need to use this method.</p>
     *
     * @return true if the token in the slot is red, false if it is yellow.
     */
    public boolean getIsRed() {
        return isRed;
    }
    /**
     * If the slot is currently empty, adds a red token to it.
     *
     * <p>Your agent WILL need to use this method.</p>
     */
    public void addRed() {
        if (!isFilled) {
            this.isFilled = true;
            this.isRed = true;
        }
    }
    /**
     * If the slot is currently empty, adds a yellow token to it.
     *
     * <p>Your agent WILL need to use this method.</p>
     */
    public void addYellow() {
        if (!isFilled) {
            this.isFilled = true;
            this.isRed = false;
        }
    }

    /**
     * Checks if the slot should be highlighted because it is part of a winning move.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @return true if the slot is highlighted, false if not.
     */
    public boolean getIsHighlighted() {
        return isHighlighted;
    }
    /**
     * Highlights the slot.
     *
     * <p>Your agent will not need to use this method.</p>
     */
    public void highlight() {
        this.isHighlighted = true;
    }
    /**
     * Clears the slot.
     *
     * <p>Your agent will not need to use this method.</p>
     */
    public void clear() {
        this.isFilled = false;
        this.isRed = false;
        this.isHighlighted = false;
    }
}
