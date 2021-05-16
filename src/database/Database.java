package database;

import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import game.Board;
import builder.TextBoardBuilder;

/**
 * Class representing a database with an open connection.
 *
 * @author Alexis Moins
 */
class Database {

    /**
     * The connection to the database
     */
    private final Connection CONNECTION;

    /**
     * The URL needed to open the connection
     */
    static final String URL = "jdbc:sqlite:";

    /**
     * The driver needed to initialise the connection
     */
    static final String DRIVER = "org.sqlite.JDBC";

    /**
     * Parameterised constructor creating a new Database object.
     *
     * @param connection the valid connection to the database
     */
    Database(Connection connection) {
        this.CONNECTION = connection;
    }

    /**
     * Return a new Database object with a valid connection.
     *
     * @param path the path leading to the database
     * @throws SQLException unable to connect to the database
     * @return a new Database object
     */
    static Database newConnection(String path) throws SQLException {
        Connection connection = DriverManager.getConnection(URL + path);
        var statement = connection.createStatement();
        statement.execute(SQLRequest.CREATE_TABLE_BOARDS);
        statement.execute(SQLRequest.CREATE_TABLE_ROWS);
        return new Database(connection);
    }

    /**
     * Add the given board to the database.
     *
     * @param ID the ID of the board in the database
     * @param board the builder of the board
     */
    void add(String ID, TextBoardBuilder board) {
        try {
            insertIntoBoardsTable(ID, board);
            insertIntoRowsTable(ID, board);
        } catch (SQLException exception) {
            System.err.println("* " + exception.getMessage());
        }
    }

    private void insertIntoBoardsTable(String ID, TextBoardBuilder board) throws SQLException {
        String SQL = SQLRequest.INSERT_INTO_BOARDS;
        var statement = this.CONNECTION.prepareStatement(SQL);
        statement.setString(1, ID);
        statement.setString(2, board.name());
        statement.setInt(3, board.length());
        statement.setInt(4, board.width());
        statement.executeUpdate();
    }

    private void insertIntoRowsTable(String ID, TextBoardBuilder board) throws SQLException {
        String SQL = SQLRequest.INSERT_INTO_ROWS;
        for (int i = 0; i < board.width(); i++) {
            try {
                var statement = this.CONNECTION.prepareStatement(SQL);
                statement.setString(1, ID);
                statement.setInt(2, i);
                statement.setString(3, board.getRow(i));
                statement.executeUpdate();
            } catch (SQLException exception) {
                throw exception;
            }
        }
    }

    void remove(String id) {

    }

    public HashMap<String, Board> getBoards() throws SQLException {
        String SQL = SQLRequest.GET_BOARDS_INFO;
        var statement = this.CONNECTION.createStatement();
        ResultSet boardInfo = statement.executeQuery(SQL);
        return populatedMap(boardInfo);
    }

    private HashMap<String, Board> populatedMap(ResultSet boardInfo) throws SQLException {
        HashMap<String, Board> boards = new HashMap<>();
        while (boardInfo.next()) {
            String ID = boardInfo.getString("boardID");
            String name = boardInfo.getString("name");
            int rows = boardInfo.getInt("rows");
            int columns = boardInfo.getInt("columns");
            boards.put(ID, new Board(name, columns, rows));
        }
        return boards;
    }

    //    Board get(String id) {
    //
    //    }

}
