package game;

/**
 * Class representing an entity on the board with a string representation and 
 * coordinates.
 *
 * @author Alexis Moins
 */
class Entity {

    /**
     * The representation of an entity was a character.
     */
    private final char character_;

    /**
     * The coordinates representing the position of the entity on the board.
     */
    private Coordinates coordinates_;

    /**
     * Parameterised constructor creating a new entity object.
     *
     * @param character the character representing the entity
     */
    Entity(char character) {
        this.coordinates_ = null;
        this.character_ = character;
    }

    /**
     * Return the coordinates of the entity.
     *
     * @return a Coordinates object
     */
    Coordinates coordinates() {
        return this.coordinates_;
    }

    /**
     * Create and return a new box entity (factory method).
     *
     * @return a box Entity
     */
    public static Entity box() {
        return new Entity('C');
    }

    /**
     * Create and return a new player entity (factory method).
     *
     * @return a player Entity
     */
    public static Entity player() {
        return new Entity('P');
    }

    /**
     * Set the entity position to the given x and y values.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPosition(int x, int y) {
        this.coordinates_ = new Coordinates(x, y);
    }

    /**
     * Return true if the entity is at the given position.
     *
     * @return a boolean
     */
    public boolean isAtPosition(Coordinates coord) {
        return this.coordinates_.equals(coord);
    }

    /**
     * Return the character representing the entity.
     *
     * @return a character
     */
    char character() {
        return this.character_;
    }

}
