package game;

import org.junit.Test;
import static org.junit.Assert.*;

import builders.FileBoardBuilder;

/**
 *
 * @author Alexis Moins
 */
public class LevelTest {
    
    @Test
    public void boxCanBeMoved() {
        FileBoardBuilder builder = FileBoardBuilder
                .deserialise("data/boards/test1.txt");
    }
    
}
