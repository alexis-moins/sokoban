
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Alexis Moins
 */
public class Board {
    
    private final String NAME;
    
    private final int LENGTH;
    
    private final int WIDTH;
    
    private char[][] OBJECTS;
    
    private ArrayList<Entity> entities;

    private HashSet<Cell> CELLS;
    
    /*
    TODO : HashSet contenant des coordonnées triées.
    Case -> coord (x & y), character (x & y + ), hasCollision (can traverse), entity (player or boxes passing by)
    */
    
    Board(String name, int length, int width) {
        this.NAME = name;
        this.LENGTH = length;
        this.WIDTH = width;
        
        this.OBJECTS = new char[length][width];
    }

    public String getNAME() {
        return NAME;
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }
    
    
    void addHorizontalWall(int x, int y, int size) {
        for (int i = x; i < size; i++)
            this.OBJECTS[i][y] = '#';
    }
    
    void addVerticalWall(int x, int y, int size) {
        for (int i = y; i < size; i++)
            this.OBJECTS[x][i] = '#';
    }
    
    void addBox(int x, int y) {
    
    }
    
    void addTarget(int x, int y) {
    
    }
    
    void setPosition(int x, int y) {
    
    }
    
    void fillWhiteSpaces() {
        for (int i = 0; i < this.LENGTH; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                // if (this.OBJECTS[i][j] ' ')
                //   this.OBJECTS[i][j] = '.';
            }
        }
    }
    
    void display() {
        System.out.print("  ");
        for (int i = 0; i <this.LENGTH; i++) {
            if (i == this.LENGTH-1)
                System.out.println(i);
            else
                System.out.print(i + " ");
        }
        
        for (int i = 0; i < this.WIDTH; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.LENGTH; j++) {
                System.out.print(this.OBJECTS[j][i] + " ");
            }
            System.out.println();
        }
    }
}
