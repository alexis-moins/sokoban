package database;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import game.Board;
import builder.TextBoardBuilder;
import exceptions.InvalidCharacterException;

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
        loadDriver();
        Connection connection = DriverManager.getConnection(URL + path);
        var statement = connection.createStatement();
        statement.execute(SQLRequest.CREATE_TABLE_BOARDS);
        statement.execute(SQLRequest.CREATE_TABLE_ROWS);
        return new Database(connection);
    }
    
    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("* " + e.getMessage());
        }
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

    /**
     * Insert the given board into the database (BOARS table) with the given ID.
     *
     * @param ID the ID of the considered board
     * @param board the TextBoardBuilder of the board
     */
    private void insertIntoBoardsTable(String ID, TextBoardBuilder board) throws SQLException {
        String SQL = SQLRequest.INSERT_INTO_BOARDS;
        var statement = this.CONNECTION.prepareStatement(SQL);
        statement.setString(1, ID);
        statement.setString(2, board.name());
        statement.setInt(3, board.length());
        statement.setInt(4, board.width());
        statement.executeUpdate();
    }

    /**
     * Insert the given board into the database (ROWS table) with the given ID.
     *
     * @param ID the ID of the considered board
     * @param board the TextBoardBuilder of the board
     */
    private void insertIntoRowsTable(String ID, TextBoardBuilder board) throws SQLException {
        String SQL = SQLRequest.INSERT_INTO_ROWS;
        for (int i = 0; i < board.width(); i++) {
            var statement = this.CONNECTION.prepareStatement(SQL);
            statement.setString(1, ID);
            statement.setInt(2, i);
            statement.setString(3, board.getRow(i));
            statement.executeUpdate();
        }
    }

    public List<String> getValidIDs() throws SQLException {
        String SQL = SQLRequest.GET_LIST_OF_ID;
        var statement = this.CONNECTION.createStatement();
        var IDs = statement.executeQuery(SQL);
        var IDList = new ArrayList<String>();
        while (IDs.next()) {
            String ID = IDs.getString("boardID");
            IDList.add(ID);
        }
        return IDList;
    }

    /**
     * Remove the board corresponding to the given ID from the database.
     *
     * @param ID the ID of the board in the database
     */
    void remove(String ID) {
        try {
            removeFromDatabase(SQLRequest.DELETE_ROWS, ID);
            removeFromDatabase(SQLRequest.DELETE_BOARD, ID);
        } catch (SQLException e) {
            System.err.println("* " + e.getMessage());
        }
    }

    /**
     * Remove the board corresponding to the given ID, using the specified SQL request.
     *
     * @param SQL a string representing the request
     * @param ID the ID of the board in the database
     */
    private void removeFromDatabase(final String SQL, final String ID) throws SQLException {
        var statement = this.CONNECTION.prepareStatement(SQL);
        statement.setString(1, ID);
        statement.executeUpdate();
    }

    /**
     * Return a map of all the boards available in the database and their ID.
     *
     * @return a HashMap of string and Board object
     */ 
    public HashMap<String, Board> getListOfBoards() throws SQLException, InvalidCharacterException {
        String SQL = SQLRequest.GET_BOARDS_INFO;
        var statement = this.CONNECTION.createStatement();
        ResultSet info = statement.executeQuery(SQL);
        HashMap<String, Board> boards = new HashMap<>();
        while (info.next()) {
            String ID = info.getString("boardID");
            String name = info.getString("name");
            boards.put(ID, getBoardWithID(ID, name));
        }
        return boards;
    }

    /**
     * Return the board corresponding to the given ID and use the given name to
     * build it.
     *
     * @param ID the ID of the considered board
     * @param name the name of the board
     * @return a Board object
     */
    public Board getBoardWithID(final String ID, final String name) throws SQLException, InvalidCharacterException {
        String SQL = SQLRequest.GET_BOARD_WITH_ID;
        var statement = this.CONNECTION.prepareStatement(SQL);
        statement.setString(1, ID);
        ResultSet info = statement.executeQuery();
        return buildBoard(info, name);
    }

    /**
     * Return the board corresponding to the given ID (overloaded method).
     *
     * @param ID the ID of the considered board
     * @return a Board object
     */
    public Board getBoardWithID(final String ID) throws SQLException, InvalidCharacterException {
        return getBoardWithID(ID, "...");
    }

    /**
     * Return a board build with the informations retrieved from the given SQL
     * ResultSet.
     *
     * @param rows the result of the SQL request
     * @return a Board object
     */
    private Board buildBoard(final ResultSet rows, final String name) throws SQLException, InvalidCharacterException {
        var builder = new TextBoardBuilder(name);
        while (rows.next()) {
            String row = rows.getString("content");
            builder.append(row);
        }
        return builder.build();
    }

}