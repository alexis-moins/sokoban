
import java.util.HashSet;

class Tile {

    private char character = '.';

    private boolean canBeCrossed = true;

    private final Coordinates COORDINATES;

    Tile(int x, int y) {
        this.COORDINATES = new Coordinates(x, y);
    }

    Tile(int x, int y, char character, boolean canBeCrossed) {
        this.character = character;
        this.canBeCrossed = canBeCrossed;
        this.COORDINATES = new Coordinates(x, y);
    }

    public static Tile wall(int x, int y) {
        var wall = new Tile(x, y, '#', false);
        return wall;
    }

    public static Tile target(int x, int y) {
        var wall = new Tile(x, y, 'T', true);
        return wall;
    }

    /**
     * Return true if the player has the same coordinates as the
     * current tile.
     *
     * @return true if the player crosses the tile.
     */
    boolean isCrossedBy(Coordinates playerCoordinates) {
        return this.COORDINATES == playerCoordinates;
    }

    /**
     * Return true if the player has the same coordinates as the
     * current tile.
     *
     * @return true if the player crosses the tile.
     */
    boolean contains(HashSet<Coordinates> boxes) {

    }

    /**
     * Accessor for the CHARACTER attriute.
     *
     * @return the current tile's character.
     */
    public char character() {
        return this.character;
    }

    public Coordinates coordinates() {
        return this.COORDINATES;
    }

    public boolean canBeCrossed() {
        return this.canBeCrossed;
    }

}
