package database;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Class testing the behaviour of methods in class Administrator.
 * 
 * @author Alexis Moins
 */
public class AdministratorTest {

    /**
     * Method testing the correct behaviour of the method manageDatabase().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test(expected = SQLException.class)
    public void manageDatabaseExceptionTest() throws SQLException {
        Administrator.manageDatabase("foo/bar.db");
    }
    
    /**
     * Method testing the correct behaviour of the method manageDatabase().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test
    public void manageDatabaseTest() throws SQLException {
        Administrator admin = Administrator.manageDatabase("data/Sokoban.db");
        assertTrue(admin != null);
    }
    
    /**
     * Method testing the correct behaviour of the method IDIsValid().
     * 
     * @throws SQLException unable to open the database file
     */
    @Test
    public void IDIsValidTest() throws SQLException {
        Administrator admin = Administrator.manageDatabase("data/Sokoban.db");
        assertTrue(admin.IDIsValid("first"));
        assertTrue(admin.IDIsValid("second"));
        assertTrue(admin.IDIsValid("invalid"));
    }

}