package database;

import java.util.HashMap;
import java.sql.SQLException;

import game.Board;
import game.Utils;
import builder.FileBoardBuilder;


/**
 * Class representing the administrator for the database.
 *
 * @author Alexis Moins
 */
public class Administrator {

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
     * @param path the path leading to the database`
     * @throws SQLException TODO
     * @return an Administrator object
     */
    public static Administrator manageDatabase(String path) throws SQLException {
        var database = Database.newConnection(path);
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
                    break;
                case "3":
                    addBoard();
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

    private void addBoard() {
        String path = Utils.askUser("\nPath to the file containing the board : ");
        var builder = FileBoardBuilder.deserialise(path);
        String ID = Utils.askUser("ID associated with the board : ");
        this.DATABASE.add(ID, builder.toTextBuilder());
    }

    /**
     * Display the list of all the boards in the database.
     */
    private void listBoards() {
        try {
            HashMap<String, Board> boards = this.DATABASE.getBoards();
            boards.forEach((ID, board) -> {
                System.out.println("\n* " + ID + " (" + board.length() + 
                        "x" + board.width() + ")");
                System.out.println(board.name());
            });
        } catch (SQLException exception) {
            System.err.println("* " + exception.getMessage());
        }
    }

}

