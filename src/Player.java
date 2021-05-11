
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Alexis Moins
 */
public class Player {

    static final PrintStream out = System.out;

    private static final Scanner in = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        out.println("Sokoban (そうこばん)");
        out.println("====================");
        out.println("1. Play a game");
        out.println("2. Quit");
        mainMenu();
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
                    loadBoard("src/board.txt");
                    break;
                case "2":
                    finished = true;
                    break;
                default:
                    out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void loadBoard(String path) {
        try {
            var builder = FileBoardBuilder.open(path);
            var board = builder.build();
            var level = new Level(board);
            level.start();
        } catch (SokobanException e) {
            System.err.println(e.getMessage());
        }
    }

}
