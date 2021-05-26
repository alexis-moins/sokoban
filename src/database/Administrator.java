package database;

import java.util.List;
import java.util.HashMap;
import java.sql.SQLException;

import game.Board;
import utils.Utils;
import builders.FileBoardBuilder;

/**
 * Class representing the administrator for the database.
 *
 * @author Alexis Moins
 */
public final class Administrator {

    /**
     * The Database object the current administrator manages.
     */
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

    /**
     * Asks the user to select an entry in the administrator menu.
     */
    public void menu() {
        boolean finished = false;
        while (!finished) {
            displayDatabaseMenu();
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
                    Utils.errorMessage("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Display the administrator menu and its different possible choices.
     */
    private void displayDatabaseMenu() {
        System.out.println("\nBoard management\n");
        System.out.println("1. List boards");
        System.out.println("2. Show board");
        System.out.println("3. Add board from a file)");
        System.out.println("4. Remove board (cautious)");
        System.out.println("5. Quit");
    }

    /**
     * Display the list of all the boards in the database.
     */
    private void listBoards() {
        try {
            HashMap<String, Board> boards = this.DATABASE.getListOfBoards();
            if (boards.isEmpty())
                Utils.errorMessage("The database contains no boards");
            else
                boards.forEach((ID, board) -> 
                        System.out.println("\n* " + ID + " " + board));
        } catch (SQLException e) {
            Utils.errorMessage(e.getMessage());
        }
    }

    /**
     * Draw the board selected by the user on the screen.
     */
    private void showBoard() {
        String ID = Utils.askUser("\nID of the board you want to see : ");
        if (!IDIsValid(ID)) {
            Utils.errorMessage("No boards found with that ID");
            return;
        }
        Board board = this.DATABASE.getBoardWithID(ID);
        board.draw();
    }

    /**
     * Ask the player for the path to a file, then add the content of the file
     * as a board in the database.
     */
    private void addBoard() {
        String path = Utils.askUser("\nPath to the file containing the board : ");
        FileBoardBuilder builder = FileBoardBuilder.deserialize(path);
        if (builder == null)
            return;
        String ID = Utils.askUser("ID associated with the board : ");
        this.DATABASE.add(ID, builder.convertToTextBuilder());
    }

    /**
     * Remove the board with the ID selected by the player from the database.
     */
    private void removeBoard() {
        String ID = Utils.askUser("\nID of the board you want to remove : ");
        if (!IDIsValid(ID)) {
            Utils.errorMessage("No boards found with that ID");
            return;
        } 
        this.DATABASE.remove(ID);
    }

    /**
     * Return the board selected by the player.
     *
     * @return a Board object
     */
    public Board selectBoard() {
        listBoards();
        String ID = Utils.askUser("\nSelect the ID of the board you want to solve : ");
        return IDIsValid(ID) ? this.DATABASE.getBoardWithID(ID) : null;
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
            Utils.errorMessage(e.getMessage());
            return false;
        }
    }

}
