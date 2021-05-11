import java.util.Set;
import java.util.HashMap;

/**
 * Class representing the board.
 *
 * @author Alexis Moins
 */
final class Board {

    /**
     * The name of the board / level.
     */
    private final String name_;

    private final int length_;

    private final int width_;

    private Entity player;

    private final HashMap<Coordinates, Tile> walls_;

    private final HashMap<Coordinates, Tile> targets_;

    private final HashMap<Coordinates, Entity> boxes_;

    /**
     * Parameterised constructor creating a new board.
     *
     * @param name the name of the board
     * @param length the length of the board
     * @param width the width of the board
     */
    Board(String name, int length, int width) {
        this.name_ = name;
        this.width_ = width;
        this.length_ = length;
        this.player = Entity.player();

        this.walls_ = new HashMap<>();
        this.boxes_ = new HashMap<>();
        this.targets_ = new HashMap<>();
    }

    /**
     * Return the set of the coordinates of each and every targets present on
     * the board.
     *
     * @return a set of coordinates
     */
    Set<Coordinates> targets() {
        return this.targets_.keySet();
    }

    /**
     * Return the set of the coordinates of each and every boxes present on
     * the board.
     *
     * @return a set of coordinates
     */
    Set<Coordinates> boxes() {
        return this.boxes_.keySet();
    }

    /**
     * Add a wall of the given size at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the wall
     * @param direction the direction of the wall
     */
    void addWall(int x, int y, int size, Direction direction) {
        var wall = Tile.wall();
        var coord = new Coordinates(x, y);
        for (int i = y; i < y + size; i++) {
            this.walls_.put(coord, wall);
            coord = coord.next(direction);
        }
    }

    /**
     * Set the player's position on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void setPlayerPosition(int x, int y) {
        this.player.setPosition(x, y);
    }

    Coordinates playerPosition() {
        return this.player.coordinates();
    }

    /**
     * Add a box on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void addBox(int x, int y) {
        var coord = new Coordinates(x, y);
        var box = Entity.box();
        this.boxes_.put(coord, box);
    }

    /**
     * Add a target on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void addTarget(int x, int y) {
        var target = Tile.target();
        var coord = new Coordinates(x, y);
        this.targets_.put(coord, target);
    }

    /**
     * Draw the board on the screen.
     */
    void draw() {
        displayColumnNumbers();
        for (int i = 0; i < this.width_; i++) {
            System.out.print("\n" + i);
            for (int j = 0; j < this.length_; j++) {
                var coord = new Coordinates(j, i);
                char tileCharacter = tileCharacter(coord);
                System.out.print(" " + tileCharacter);
            }
        }
        System.out.print("\n");
    }

    /**
     * Return the adequate character to represent the given tile on the board.
     *
     * @return a character
     */
    char tileCharacter(Coordinates coord) {
        char tileCharacter = '.';
        if (this.player.isAtPosition(coord))
            tileCharacter = this.player.character();
        else if (this.boxes_.containsKey(coord))
            tileCharacter = this.boxes_.get(coord).character();
        else if (this.walls_.containsKey(coord))
            tileCharacter = this.walls_.get(coord).character();
        return tileCharacter;
    }

    /**
     * Display the column numbers on the board.
     */
    void displayColumnNumbers() {
        System.out.print(" ");
        for (int i = 0; i < this.length_; i++) {
            System.out.print(" " + i);
        }
    }
}
