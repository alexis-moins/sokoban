package elements;

import utils.Type;
import utils.Coordinates;

/**
 * Class representing an entity on the board that inherits from the BoardElement
 * abstract class.
 *
 * @author Alexis Moins
 */
public class Entity extends BoardElement {

    /**
     * Parameterised constructor creating a new entity object.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @param type the type of the element
     */
    private Entity(final int x, final int y, final Type type) {
        super(x, y, type);
    }

    /**
     * Create and return a new box entity (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a box Entity
     */
    public static Entity newBox(final int x, final int y) {
        return new Entity(x, y, Type.BOX);
    }

    /**
     * Create and return a new player entity (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a player Entity
     */
    public static Entity newPlayer(final int x, final int y) {
        return new Entity(x, y, Type.PLAYER);
    }

    /**
     * Set the entity position to the given coordinates.
     *
     * @param coord the new position
     */
    public void setPosition(final Coordinates coord) {
        this.coordinates = new Coordinates(coord.x(), coord.y());
    }

}
