package utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class testing the behaviour of methods in class Coordinates.
 * 
 * @author Alexis Moins
 */
public class CoordinatesTest {

    /**
     * Method testing the correct behaviour of the method next().
     */
    @Test
    public void nextTest() {
        assertEquals(new Coordinates(0, 0), new Coordinates(0, 1).next(Direction.NORTH));
        assertEquals(new Coordinates(0, 1), new Coordinates(0, 0).next(Direction.SOUTH));
        assertEquals(new Coordinates(1, 0), new Coordinates(0, 0).next(Direction.EAST));
        assertEquals(new Coordinates(0, 0), new Coordinates(1, 0).next(Direction.WEST));
    }

}
