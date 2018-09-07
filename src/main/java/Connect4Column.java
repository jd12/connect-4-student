/**
 * A single column in a Connect 4 game. A column stores a number of individual slots.
 *
 * <p>You should not modify this class, but you will need to use methods within it.</p>
 */
public class Connect4Column {

    /**
     * The Connect4Slots associated with this column.
     */
    private Connect4Slot[] slots;

    /**
     * Creates a new Connect4Column with a given height.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param height the height of the column.
     */
    public Connect4Column(int height) {
        slots = new Connect4Slot[height];
        for (int i = 0; i < height; i++) {
            slots[i] = new Connect4Slot();
        }
    }
    /**
     * Creates a copy of the given Connect4Column.
     *
     * <p>Your agent will not need to use this method.</p>
     *
     * @param column the column to copy.
     */
    public Connect4Column(Connect4Column column) {
        this.slots = new Connect4Slot[column.getRowCount()];
        for (int i = 0; i < column.getRowCount(); i++) {
            slots[i] = new Connect4Slot(column.getSlot(i));
        }
    }
    /**
     * Returns a single Connect4Slot from the column.
     *
     * <p>Your agent WILL need to use this method.</p>
     *
     * @param i the Connect4Slot to retrieve.
     * @return the Connect4Slot at that index.
     */
    public Connect4Slot getSlot(int i) {
        if (i < slots.length && i >= 0) {
            return slots[i];
        } else {
            return null;
        }
    }
    /**
     * Checks if the column is full.
     *
     * <p>Your agent WILL need to use this method.</p>
     *
     * @return true if the column is full, false otherwise.
     */
    public boolean getIsFull() {
        for (Connect4Slot slot : slots) {
            if (!slot.getIsFilled()) {
                return false;
            }
        }
        return true;
    }
    /**
     * Returns the number of rows in the column.
     *
     * <p>Your agent WILL need to use this method.</p>
     *
     * @return the number of rows in the column.
     */
    public int getRowCount() {
        return slots.length;
    }
}
