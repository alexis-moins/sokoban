package builders;

import game.Board;

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
     */
    Board build();

}
