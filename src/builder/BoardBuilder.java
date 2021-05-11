package builder;

import game.Board;
import exceptions.SokobanException;

/**
 * Interface listing the behaviour for builder classes.
 *
 * @author Alexis Moins
 */
interface BoardBuilder {

    Board build() throws SokobanException;

}
