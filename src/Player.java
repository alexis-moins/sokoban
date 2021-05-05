
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
        out.println("Sokoban (倉庫番)");
        out.println("===============\n");
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
                    newGame();
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
    
    private static void newGame(Board board) {
        boolean finished = false;
        while (!finished) {
            String move = getUserInput();
            switch (move) {
                case "/quit":
                    finished = true;
                    break;
                case "R":
                    
                    break;
            }
        }
        
        Board b = new Board("TEST", 9, 6);
        b.addHorizontalWall(2, 0, 4);
        b.addHorizontalWall(5, 1, 4);
        b.addHorizontalWall(0, 5, 9);
        b.addHorizontalWall(1, 1, 2);
        
        b.addVerticalWall(0, 1, 5);
        b.addVerticalWall(8, 1, 8);
        b.addVerticalWall(5, 3, 3);
        b.addVerticalWall(2, 3, 1);
        
        b.setPosition(6, 4);
        
        b.addBox(6, 2);
        b.addBox(6, 3);
        
        b.addTarget(2, 4);
        b.addTarget(4, 4);
        b.display();
    }

}