/**
 *
 * @author Alexis Moins
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board("TEST", 6, 4);
        b.addHorizontalWall(0, 0, 6);
        b.addVerticalWall(0, 1, 4);
        b.fillWhiteSpaces();
        b.display();
    }
    
}