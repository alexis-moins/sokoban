/**
 * Class implementing an exception when an invalid character has been found in
 * the parsed file.
 *
 * @author Alexis Moins
 */
class InvalidCharacterException extends SokobanException {

    /**
     * Parameterised constructor creating a new exception.
     * 
     * @param character the invalid character.
     */
    public InvalidCharacterException(String character) {
        super("Unable to build board : invalid character '" + character + 
                "' found");
    }

}
