package game;

import java.util.Scanner;

/**
 * Class containing utility methods.
 *
 * @author Alexis Moins
 */
public class Utils {

    /**
     * Print a message on screen and return the input typed by the player.
     *
     * @param message the string that will be printed
     * @return a string
     */
    static public String askUser(String message) {
        System.out.print(message);
        return getInput();
    }

    /**
     * Return the user input.
     *
     * @return a string
     */
    static public String getInput() {
        var reader = new Scanner(System.in);
        return reader.nextLine().trim();
    }

    /**
     * Clear the screen by display one hundred new lines on screen.
     */
    static public void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

}

