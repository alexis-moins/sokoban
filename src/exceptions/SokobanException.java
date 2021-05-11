package exceptions;

/**
 * Class implementing the generic exception for the game.
 *
 * @author Alexis Moins
 */
public class SokobanException extends Exception {

    /**
     * Parameterised constructor creating a new exception.
     * 
     * @param message the precise exception message
     */
    public SokobanException(String message) {
        super(message);
    }

}

