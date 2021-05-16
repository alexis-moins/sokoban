package game;

import java.util.Scanner;

/**
 * Class representing .
 *
 * @author Alexis Moins
 */
public class Utils {

    static public String askUser(String question) {
        System.out.print(question);
        return getInput();
    }

    static public String getInput() {
        var reader = new Scanner(System.in);
        return reader.nextLine().trim();
    }

}

