package builder;

import game.Board;
import exceptions.InvalidCharacterException;

/**
 * Interface listing the behaviour for builder classes.
 *
 * @author Alexis Moins
 */
interface BoardBuilder {

    /**
     * Return a board created with the current builder informations.
     *
     * @return a Board object
     * @throws InvalidCharacterException an invalid character prevents the 
     * board from being built
     */
    Board build() throws InvalidCharacterException;

}