package exceptions;

/**
 * Class implementing an exception when the direction given by the player is not
 * valid on the board.
 *
 * @author Alexis Moins
 */
public final class InvalidDirectionException extends Exception {

    /**
     * Parameterised constructor creating a new exception.
     *
     * @param direction the invalid direction
     */
    public InvalidDirectionException(final char direction) {
        super("Invalid direction '" + direction + "'");
    }

}
