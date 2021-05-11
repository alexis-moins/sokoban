/**
 * Class representing a tile on the board with a string representation and 
 * information about its collisions.
 *
 * @author Alexis Moins
 */
class Tile {

    /**
     * The CHARACTER representation of the tile.
     */
    private final char CHARACTER;

    /**
     * True if entities like boxes and the player can cross the tile.
     */
    private final boolean CAN_BE_CROSSED;

    /**
     * Parameterised constructor creating a new Tile object.
     */
    Tile(char character, boolean canBeCrossed) {
        this.CHARACTER = character;
        this.CAN_BE_CROSSED = canBeCrossed;
    }

    /**
     * Return a new Tile object representing a wall.
     *
     * @return a tile object.
     */
    public static Tile wall() {
        return new Tile('#', false);
    }

    /**
     * Return a new Tile object representing a target.
     *
     * @return a tile object.
     */
    public static Tile target() {
        return new Tile('x', true);
    }

    /**
     * Return true if the tile can be crossed by entities.
     *
     * @return a boolean.
     */
    public boolean canBeCrossed() {
        return this.CAN_BE_CROSSED;
    }

    /**
     * Return the representation of the tile.
     *
     * @return a character
     */
    char character() {
        return this.CHARACTER;
    }

}
