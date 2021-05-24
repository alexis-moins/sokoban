package database;

import java.util.List;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class testing the behaviour of methods in class Database.
 * 
 * @author Alexis Moins
 */
public class DatabaseTest {

    /**
     * Method testing the correct behaviour of the method newConnection().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test(expected = SQLException.class)
    public void newConnectionExceptionTest() throws SQLException {
        Database.newConnection("foo/bar.db");
    }
    
    /**
     * Method testing the correct behaviour of the method newConnection().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test
    public void newConnectionTest() throws SQLException {
        Database database = Database.newConnection("data/Sokoban.db");
        assertTrue(database != null);
    }
    
    /**
     * Method testing the correct behaviour of the method newConnection().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test
    public void getListsOfIDsTest() throws SQLException {
        Database database = Database.newConnection("data/Sokoban.db");
        List<String> IDs = database.getListOfValidIDs();
        assertEquals(3, IDs.size());

        assertTrue(IDs.contains("first"));
        assertTrue(IDs.contains("second"));
        assertTrue(IDs.contains("invalid"));
    }
    
}