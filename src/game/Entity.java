package game;

/**
 * Class representing an entity on the board with a string representation and 
 * coordinates.
 *
 * @author Alexis Moins
 */
class Entity extends BoardElement {

    /**
     * Parameterised constructor creating a new entity object.
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @param type the type of the element
     */
    Entity(int x, int y, Type type) {
        super(x, y, type);
    }

    /**
     * Create and return a new box entity (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a box Entity
     */
    public static Entity newBox(int x, int y) {
        return new Entity(x, y, Type.BOX);
    }

    /**
     * Create and return a new player entity (factory method).
     *
     * @param x the position on the x axis
     * @param y the position on the y axis
     * @return a player Entity
     */
    public static Entity newPlayer(int x, int y) {
        return new Entity(x, y, Type.PLAYER);
    }

    /**
     * Set the entity position to the given coordinates.
     *
     * @param coord the new position
     */
    public void setPosition(Coordinates coord) {
        this.coordinates = new Coordinates(coord.x(), coord.y());
    }

}
