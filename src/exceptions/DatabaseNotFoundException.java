package exceptions;

/**
 * Class implementing an exception when an invalid character has been found in
 * the parsed file.
 *
 * @author Alexis Moins
 */
public class DatabaseNotFoundException extends SokobanException {

    /**
     * Parameterised constructor creating a new exception.
     */
    public DatabaseNotFoundException(String path) {
        super("* Database '" + path + "' not found.");
    }

}
