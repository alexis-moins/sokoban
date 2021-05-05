
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alexis Moins
 */
class Board {
    
    private final String NAME;

    private final int LENGTH;

    private final int WIDTH;

    private Coordinates player;
    
    private final HashMap<Coordinates, Tile> TILES;

    private final ArrayList<Coordinates> BOXES;

    Board(String name, int length, int width) {
        this.NAME = name;
        this.LENGTH = length;
        this.WIDTH = width;
        
        this.TILES = new HashMap<>();
        this.BOXES = new ArrayList<>();
    }

    void addHorizontalWall(int x, int y, int size) {
        for (int i = x; i < x+size; i++) {
            var wall = Tile.wall(x, y);
            var coord = new Coordinates(i, y);
            this.TILES.put(coord, wall);
        }
    }

    void addVerticalWall(int x, int y, int size) {
        for (int i = y; i < y+size; i++) {
            var wall = Tile.wall(x, y);
            var coord = new Coordinates(x, i);
            this.TILES.put(coord, wall);
        }
    }

    void setPosition(int x, int y) {
        this.player = new Coordinates(x, y);
    }

    void addBox(int x, int y) {
        var coord = new Coordinates(x, y);
        this.BOXES.add(coord);
    }
    
    void addTarget(int x, int y) {
        var target = Tile.target(x, y);
        var coord = new Coordinates(x, y);
        this.TILES.put(coord, target);
    }

    void display() {
        displayColumnNumbers();
        for (int i = 0; i < this.WIDTH; i++) {
            System.out.print("\n" + i);
            for (int j = 0; j < this.LENGTH; j++) {
                var coord = new Coordinates(j, i);
                if (this.player.equals(coord)) {
                    System.out.print(" " + 'P');
                } else if (this.BOXES.contains(coord)) {
                    System.out.print(" " + 'C');
                } else if (this.TILES.containsKey(coord)) {
                    System.out.print(" " + this.TILES.get(coord)
                            .character());
                } else {
                    System.out.print(" " + '.');
                }
            }
        }
        System.out.println(" ");
    }
    
    void displayColumnNumbers() {
        System.out.print(" ");
        for (int i = 0; i < this.LENGTH; i++) {
            System.out.print(" " + i);
        }
    }

}
