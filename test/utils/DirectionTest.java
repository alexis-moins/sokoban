package utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Class testing the behaviour of methods in class Direction.
 * 
 * @author Alexis Moins
 */
public class DirectionTest {

    /**
     * Method testing the correct behaviour of the method correspondingTo().
     */
    @Test
    public void correspondingToTest() {
        assertEquals(Direction.correspondingTo('u'), Direction.NORTH);
        assertEquals(Direction.correspondingTo('d'), Direction.SOUTH);
        assertEquals(Direction.correspondingTo('r'), Direction.EAST);
        assertEquals(Direction.correspondingTo('l'), Direction.WEST);
    }
    
}