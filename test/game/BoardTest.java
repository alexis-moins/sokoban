package game;

import java.util.List;

import elements.Tile;
import elements.Entity;
import elements.BoardElement;

import utils.Type;
import utils.Coordinates;

import builders.FileBoardBuilder;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class testing the behaviour of methods in class Board.
 * 
 * @author Alexis Moins
 */
public class BoardTest {

    /**
     * Method testing the correct behaviour of the method targets().
     */
    @Test
    public void targetsTest() {
        FileBoardBuilder builder = FileBoardBuilder.deserialise("data/boards/first.txt");
        Board board = builder.build();
        List<Tile> tiles = board.targets();
        assertEquals(2, tiles.size());

        Tile target1 = board.findTile(new Coordinates(1, 1));
        assertTrue(tiles.contains(target1));
        Tile target2 = board.findTile(new Coordinates(3, 1));
        assertTrue(tiles.contains(target2));
    }

    /**
     * Method testing the correct behaviour of the method boxes().
     */
    @Test
    public void boxesTest() {
        FileBoardBuilder builder = FileBoardBuilder.deserialise("data/boards/first.txt");
        Board board = builder.build();
        List<Entity> entities = board.boxes();
        assertEquals(2, entities.size());

        Entity entity1 = board.findEntity(new Coordinates(4, 2));
        assertTrue(entities.contains(entity1));
        Entity entity2 = board.findEntity(new Coordinates(5, 3));
        assertTrue(entities.contains(entity2));
    }

    /**
     * Method testing the correct behaviour of the method findElement().
     */
    @Test
    public void findElementTest() {
        FileBoardBuilder builder = FileBoardBuilder.deserialise("data/boards/first.txt");
        Board board = builder.build();

        Coordinates coord = new Coordinates(2, 1);
        BoardElement element = board.findElement(coord);
        assertTrue(element == null);

        coord = new Coordinates(0, 0);
        element = board.findElement(coord);
        assertTrue(element.isOfType(Type.WALL));

        coord = new Coordinates(1, 1);
        element = board.findElement(coord);
        assertTrue(element.isOfType(Type.TARGET));

        coord = new Coordinates(4, 2);
        element = board.findElement(coord);
        assertTrue(element.isOfType(Type.BOX));
    }

}