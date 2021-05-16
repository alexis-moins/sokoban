package database;

/**
 * Class containing the different SQL requests executed on the database.
 *
 * @author Alexis Moins
 */
class SQLRequest {

    /**
     * Request for creating a table BOARDS if it doesn't already exist
     */
    static final String CREATE_TABLE_BOARDS = "CREATE TABLE IF NOT EXISTS BOARDS (" + 
        "boardID TEXT NOT NULL," +
        "name TEXT NOT NULL," +
        "rows INT NOT NULL," +
        "columns INT NOT NULL);";

    static final String CREATE_TABLE_ROWS = "CREATE TABLE IF NOT EXISTS ROWS (" + 
        "boardID TEXT NOT NULL," +
        "rowNumber INT NOT NULL," +
        "content TEXT NOT NULL);";

    static final String INSERT_INTO_BOARDS = "INSERT INTO BOARDS" +
        "('boardID', 'name', 'rows', 'columns') VALUES (?, ?, ?, ?)";

    static final String INSERT_INTO_ROWS = "INSERT INTO ROWS" +
        "('boardID', 'rowNumber', 'content') VALUES (?, ?, ?)";

    static final String GET_BOARDS_INFO = "SELECT * FROM BOARDS";

}
