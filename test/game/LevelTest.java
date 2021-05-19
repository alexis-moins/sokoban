package game;

import org.junit.Test;
import static org.junit.Assert.*;

import builder.FileBoardBuilder;
import exceptions.InvalidCharacterException;

/**
 *
 * @author Alexis Moins
 */
public class LevelTest {
    
    @Test
    public void boxCanBeMoved() {
        FileBoardBuilder builder = FileBoardBuilder
                .deserialise("data/boards/test1.txt");
        try {
            
        } catch (InvalidCharacterException e) {
            assertTrue(false);
        }
    }
    
}
