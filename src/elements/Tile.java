package elements;

import utils.Type;

/**
 * Class representing a tile on the board that inherits from the BoardElement
 * abstract class.
 *
 * @author Alexis Moins
 */
public final class Tile extends BoardElement {

    /**
     * Parameterised constructor creating a new Tile object.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @param type the type of the element
     */
    Tile(final int x, final int y, final Type type) {
        super(x, y, type);
    }

    /**
     * Create and return a new wall tile (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a Tile object.
     */
    public static Tile newWall(final int x, final int y) {
        return new Tile(x, y, Type.WALL);
    }

    /**
     * Create and return a new target tile (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a Tile object.
     */
    public static Tile newTarget(final int x, final int y) {
        return new Tile(x, y, Type.TARGET);
    }

}
