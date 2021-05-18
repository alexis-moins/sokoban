package app;

import java.util.Scanner;
import java.sql.SQLException;

import game.Level;
import builder.FileBoardBuilder;
import database.Administrator;
import exceptions.SokobanException;

/**
 *
 * @author Alexis Moins
 */
public class Player {

    private static final String PATH = "data/Sokoban.db";

    private static final Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        displayMainMenu();
        mainMenu();
    }
    
    private static void displayMainMenu() {
        System.out.println("\nSokoban (そうこばん)\n");
        System.out.println("1. Play a game");
        System.out.println("2. Manage boards");
        System.out.println("3. Quit");
    }

    /**
     * Asks the user for input.
     * 
     * @return the user input
     */
    private static String getUserInput() {
        return in.nextLine().trim();
    }

    /**
     * Asks the user to select an entry in the main menu.
     */
    private static void mainMenu() {
        String choice;
        boolean finished = false;
        while (!finished) {
            choice = getUserInput();
            switch (choice) {
                case "1":
                    // select board from the database
                    break;
                case "2":
                    manageBoards();
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

    private static void manageBoards() {
        try {
            var administrator = Administrator.manageDatabase(PATH);
            administrator.menu();
        } catch (SQLException exception) {
            System.err.println("* " + exception.getMessage());
        }
    }

}
