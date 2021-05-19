package app;

import java.util.Scanner;
import java.sql.SQLException;

import game.Utils;
import game.Level;
import database.Administrator;
import builder.FileBoardBuilder;
import exceptions.InvalidCharacterException;

/**
 * Class used as the main interface with the player.
 *
 * @author Alexis Moins
 */
public class Player {

    /**
     * The path to the database of the game.
     */
    private static final String PATH = "data/Sokoban.db";

    /**
     * The main method of the game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            var admin = Administrator.manageDatabase(PATH);
            displayMainMenu();
            mainMenu(admin);
        } catch (SQLException e) {
            System.err.println("* " + e.getMessage());
            System.exit(1);
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nSokoban (そうこばん)\n");
        System.out.println("1. Play a game");
        System.out.println("2. Manage boards");
        System.out.println("3. Quit");
    }

    /**
     * Asks the user to select an entry in the main menu.
     */
    private static void mainMenu(Administrator admin) {
        String choice;
        boolean finished = false;
        while (!finished) {
            choice = Utils.getInput();
            switch (choice) {
                case "1":
                    selectBoard(admin);
                    break;
                case "2":
                    admin.menu();
                    displayMainMenu();
                    break;
                case "3":
                    finished = true;
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Ask the player to select a board from the available boards of the
     * database then start playing on the level created with the board.
     *
     * @param admin an Administrator object
     */
    private static void selectBoard(Administrator admin) {
        try {
            var board = admin.selectBoard();
            var level = new Level(board);
            level.start();
        } catch (SQLException | InvalidCharacterException e) {
            System.err.println("* " + e.getMessage());
        }
    }

}
