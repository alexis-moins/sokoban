package exceptions;

/**
 * Class implementing an exception the player input is not a valid direction.
 *
 * @author Alexis Moins
 */
public class InvalidDirectionException extends SokobanException {

    /**
     * Parameterised constructor creating a new exception.
     * 
     * @param direction the invalid direction.
     */
    public InvalidDirectionException(String direction) {
        super("Invalid direction '" + direction + "'");
    }

}
