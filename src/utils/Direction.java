package utils;

/**
 * Class representing the four possible directions on the board.
 *
 * @author Alexis Moins
 */
public enum Direction {

    /**
     * The direction representing the north.
     */
    NORTH(0, -1),

    /**
     * The direction representing the south.
     */
    SOUTH(0, 1),

    /**
     * The direction representing the east.
     */
    EAST(1, 0),
    /**
     * The direction representing the west.
     */
    WEST(-1, 0);

    /**
     * The translation on the x axis associated with this direction.
     */
    private final int X_TRANSLATION;

    /**
     * The translation on the y axis associated with this direction.
     */
    private final int Y_TRANSLATION;

    /**
     * Parametorised constructor creating a new Direction object.
     *
     * @param xTranslation the translation on the x axis
     * @param yTranslation the translation on the y axis
     */
    private Direction(final int xTranslation, final int yTranslation) {
        this.X_TRANSLATION = xTranslation;
        this.Y_TRANSLATION = yTranslation;
    }

    /**
     * Return the translation on the x axis when going in that direction.
     *
     * @return an integer
     */
    public int xTranslation() {
        return this.X_TRANSLATION;
    }

    /**
     * Return the translation on the y axis when going in that direction.
     *
     * @return an integer
     */
    public int yTranslation() {
        return this.Y_TRANSLATION;
    }

    /**
     * Return the direction corresponding to the given char.
     *
     * @param move the considered move on the board
     * @return a Direction object
     */
    public static Direction correspondingTo(final char move) {
        Direction direction = null;
        switch (move) {
            case 'u':
                direction = Direction.NORTH;
                break;
            case 'd':
                direction = Direction.SOUTH;
                break;
            case 'r':
                direction = Direction.EAST;
                break;
            case 'l':
                direction = Direction.WEST;
                break;
        }
        return direction;
    }

}
