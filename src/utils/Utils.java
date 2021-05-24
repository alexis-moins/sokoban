package utils;

import java.util.Scanner;

/**
 * Class containing utility methods.
 *
 * @author Alexis Moins
 */
public class Utils {

    /**
     * The ASCII escape codes to produce red-colored output.
     */
    private static final String ERROR = "\u001B[31m";

    /**
     * The ASCII escape codes to produce white-colored output.
     */
    private static final String NORMAL = "\u001B[0m";

    /**
     * Print a message on screen and return the input typed by the player.
     *
     * @param message the string that will be printed
     * @return a string
     */
    public static String askUser(String message) {
        System.out.print(message);
        return getInput();
    }

    /**
     * Return the user input.
     *
     * @return a string
     */
    public static String getInput() {
        var reader = new Scanner(System.in);
        return reader.nextLine().trim();
    }

    /**
     * Clear the screen by display one hundred new lines on screen.
     */
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    /**
     * Print the given string on screen as an error message (red foreground on
     * normal background).
     * 
     * @param message the error message
     */
    public static void errorMessage(final String message) {
        System.out.println(ERROR + message + NORMAL);
    }

}

