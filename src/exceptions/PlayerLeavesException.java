package exceptions;

/**
 * Class implementing an exception when the player wants to leave the game.
 *
 * @author Alexis Moins
 */
public final class PlayerLeavesException extends Exception {

    /**
     * Parameterised constructor creating a new exception.
     */
    public PlayerLeavesException() {
        super("The player leaves the level");
    }

}