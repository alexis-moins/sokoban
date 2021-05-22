package elements;

import utils.Coordinates;
import utils.Type;

/**
 * Abstract class representing general elements on the board.
 *
 * @author Alexis Moins
 */
public abstract class BoardElement {

    /**
     * The type of the element.
     */
    protected final Type TYPE;

    /**
     * The coordinates representing the position of the element on the board.
     */
    protected Coordinates coordinates;

    /**
     * Parameterised constructor creating a new element on the board.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @param type the type of the element
     */
    BoardElement(final int x, final int y, final Type type) {
        this.TYPE = type;
        this.coordinates = new Coordinates(x, y);
    }

    /**
     * Return the visual representation of the element on the board.
     *
     * @return a character
     */
    public char character() {
        return this.TYPE.character();
    }

    /**
     * Return the coordinates of the element.
     *
     * @return a Coordinates object
     */
    public Coordinates coordinates() {
        return this.coordinates;
    }

    /**
     * Return true if the element has collisions on the board.
     *
     * @return a boolean
     */
    public boolean hasCollisions() {
        return this.TYPE.hasCollisions();
    }

    /**
     * return true if the considered element is of the given type.
     *
     * @param type the type compared to
     * @return a boolean
     */
    public boolean isOfType(final Type type) {
        return this.TYPE == type;
    }

    /**
     * Return true if the element is at the given position.
     *
     * @param coord the considered coordinates
     * @return a boolean
     */
    public boolean isAtPosition(final Coordinates coord) {
        return this.coordinates.equals(coord);
    }

}
