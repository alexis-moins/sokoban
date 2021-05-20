package database;

import java.util.List;
import java.util.HashMap;
import java.sql.SQLException;

import game.Board;
import game.Utils;
import builder.FileBoardBuilder;
import exceptions.InvalidCharacterException;

/**
 * Class representing the administrator for the database.
 *
 * @author Alexis Moins
 */
public final class Administrator {

    private final Database DATABASE;

    /**
     * Parameterised constructor creating a new Administrator object.
     *
     * @param database a Database object
     */
    Administrator(Database database) {
        this.DATABASE = database;
    }

    /**
     * Return a new Administrator object with a valid connection to the
     * database.
     *
     * @param path the path leading to the database
     * @throws SQLException there was a problem in the database creation
     * @return an Administrator object
     */
    public static Administrator manageDatabase(String path) throws SQLException {
        Database database = Database.newConnection(path);
        return new Administrator(database);
    }

    public void menu() {
        boolean finished = false;
        while (!finished) {
            informations();
            String choice = Utils.getInput();
            switch (choice) {
                case "1":
                    listBoards();
                    break;
                case "2":
                    showBoard();
                    break;
                case "3":
                    addBoard();
                    break;
                case "4":
                    removeBoard();
                    break;
                case "5":
                    finished = true;
                    break;
                default:
                    System.err.println("* Invalid choice");
                    break;
            }
        }
    }

    private void informations() {
        System.out.println("\nBoard management\n");
        System.out.println("1. List boards");
        System.out.println("2. Show board");
        System.out.println("3. Add board (from a file)");
        System.out.println("4. Remove board");
        System.out.println("5. Quit");
    }

    /**
     * Display the list of all the boards in the database.
     */
    private void listBoards() {
        try {
            HashMap<String, Board> boards = this.DATABASE.getListOfBoards();
            if (boards.isEmpty())
                System.err.println("No board in the database");
            else
                boards.forEach((ID, board) -> 
                        System.out.println("\n* " + ID + " " + board));
        } catch (SQLException | InvalidCharacterException e) {
            System.err.println("* " + e.getMessage());
        }
    }

    private void showBoard() {
        String ID = Utils.askUser("\nID of the board you want to see : ");
        if (!IDIsValid(ID)) {
            System.err.println("* No boards were found with that ID");
            return;
        }

        try {
            Board board = this.DATABASE.getBoardWithID(ID);
            System.out.println();
            board.draw();
        } catch (InvalidCharacterException | SQLException e) {
            System.err.println("* " + e.getMessage());
        }
    }

    private void addBoard() {
        String path = Utils.askUser("\nPath to the file containing the board : ");
        FileBoardBuilder builder = FileBoardBuilder.deserialise(path);
        if (builder == null)
            return;
        String ID = Utils.askUser("ID associated with the board : ");
        this.DATABASE.add(ID, builder.convertToTextBuilder());
    }

    private void removeBoard() {
        String ID = Utils.askUser("\nID of the board you want to remove : ");
        if (!IDIsValid(ID)) {
            System.err.println("* No boards were found with that ID");
            return;
        } 
        this.DATABASE.remove(ID);
    }

    public Board selectBoard() {
        listBoards();
        String ID = Utils.askUser("\nSelect the ID of the board you want to solve : ");
        if (!IDIsValid(ID))
            return null;
        return this.DATABASE.getBoardWithID(ID);
    }

    /**
     * Return true if the given ID is present in the database.
     * 
     * @param ID the considered ID
     * @return a boolean
     */
    public boolean IDIsValid(String ID) {
        try {
            List<String> IDs = this.DATABASE.getListOfValidIDs();
            return IDs.contains(ID);
        } catch (SQLException e) {
            System.err.println("* " + e.getMessage());
            return false;
        }
    }

}

