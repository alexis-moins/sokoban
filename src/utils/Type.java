package utils;

/**
 * Enumeration listing all the type of elements that can be created.
 *
 * @author Alexis Moins
 */
public enum Type {

    /**
     * The type representing a box.
     */
    BOX('C', true),

    /**
     * The type representing a wall.
     */
    WALL('#', true),

    /**
     * The type representing a player.
     */
    PLAYER('P', false),

    /**
     * The type representing a target.
     */
    TARGET('x', false);

    /**
     * The character associated with this Type.
     */
    private final char CHARACTER;

    /**
     * Wether the type allows collisions or not.
     */
    private final boolean HAS_COLLISIONS;

    /**
     * Parametorised constructor creating a new Type object.
     * 
     * @param character the character representing the type on the board
     * @param collisions wether the considered type has collisions
     */
    private Type(final char character, final boolean collisions) {
        this.CHARACTER = character;
        this.HAS_COLLISIONS = collisions;
    }

    /**
     * Return the character representing visually the element of the
     * correspondin type on the board.
     *
     * @return a character
     */
    public char character() {
        return this.CHARACTER;
    }

    /**
     * Return true if an element of the considered type can be crossed by the
     * player on the board.
     *
     * @return a boolean
     */
    public boolean hasCollisions() {
        return this.HAS_COLLISIONS;
    }

}
