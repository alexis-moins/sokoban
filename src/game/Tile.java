package game;

/**
 * Class representing a tile on the board with a string representation and 
 * information about its collisions.
 *
 * @author Alexis Moins
 */
final class Tile extends BoardElement {

    /**
     * Parameterised constructor creating a new Tile object.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @param type the type of the element
     */
    Tile(int x, int y, Type type) {
        super(x, y, type);
    }

    /**
     * Return a new Tile object representing a wall.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a Tile object.
     */
    public static Tile newWall(int x, int y) {
        return new Tile(x, y, Type.WALL);
    }

    /**
     * Return a new Tile object representing a target.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a Tile object.
     */
    public static Tile newTarget(int x, int y) {
        return new Tile(x, y, Type.TARGET);
    }

}
