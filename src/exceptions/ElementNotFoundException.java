package exceptions;

import game.Coordinates;

/**
 * Class implementing an exception when an invalid character has been found in
 * the parsed file.
 *
 * @author Alexis Moins
 */
public class ElementNotFoundException extends SokobanException {

    /**
     * Parameterised constructor creating a new exception.
     */
    public ElementNotFoundException(Coordinates coord) {
        super("No element found at coordinates '" + coord);
    }

}