package database;

/**
 * Class containing the different SQL requests executed on the database.
 *
 * @author Alexis Moins
 */
final class SQLRequest {

    /**
     * Request for creating the table BOARDS if it doesn't already exist.
     */
    static final String CREATE_TABLE_BOARDS = "CREATE TABLE IF NOT EXISTS BOARDS (" + 
        "boardID TEXT NOT NULL," +
        "name TEXT NOT NULL," +
        "rows INT NOT NULL," +
        "columns INT NOT NULL);";

    /**
     * Request for creating the table ROWS if it doesn't already exist.
     */
    static final String CREATE_TABLE_ROWS = "CREATE TABLE IF NOT EXISTS ROWS (" + 
        "boardID TEXT NOT NULL," +
        "rowNumber INT NOT NULL," +
        "content TEXT NOT NULL);";

    /**
     * Request for inserting values into the BOARDS table.
     */
    static final String INSERT_INTO_BOARDS = "INSERT INTO BOARDS" +
        "('boardID', 'name', 'rows', 'columns') VALUES (?, ?, ?, ?)";

    /**
     * Request for inserting values into the ROWS table.
     */
    static final String INSERT_INTO_ROWS = "INSERT INTO ROWS" +
        "('boardID', 'rowNumber', 'content') VALUES (?, ?, ?)";

    /**
     * Request for retrieving info from the BOARDS table.
     */
    static final String GET_BOARDS_INFO = "SELECT * FROM BOARDS";

    /**
     * Request for retrieving info from the ROWS table.
     */
    static final String GET_BOARD_WITH_ID = "SELECT * FROM ROWS WHERE boardID = ?";

    /**
     * Request for deleting info from the BOARDS table.
     */
    static final String DELETE_ROWS = "DELETE FROM ROWS WHERE boardID = ?";

    /**
     * Request for deleting info from the ROWS table.
     */
    static final String DELETE_BOARD = "DELETE FROM BOARDS WHERE boardID = ?";

    /**
     * Request for retrieving all the valid IDs contained in the database.
     */
    static final String GET_LIST_OF_ID = "SELECT boardID FROM BOARDS";

}
