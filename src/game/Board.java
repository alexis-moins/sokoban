package game;

import exceptions.ElementNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class representing the board.
 *
 * @author Alexis Moins
 */
public final class Board {

    /**
     * The name of the board / level.
     */
    private final String NAME;

    private final int WIDTH;
    
    private final int LENGTH;

    private final ArrayList<Tile> TILES;

    private final ArrayList<Entity> ENTITIES;

    /**
     * Parameterised constructor creating a new board.
     *
     * @param name the name of the board
     * @param length the length of the board
     * @param width the width of the board
     */
    public Board(String name, int length, int width) {
        this.NAME = name;
        this.WIDTH = width;
        this.LENGTH = length;

        this.TILES = new ArrayList<>();
        this.ENTITIES = new ArrayList<>();
    }

    /**
     * Return the set of each and every targets present on the board.
     *
     * @return a list of Tile objects
     */
    List<Tile> targets() {
        return this.TILES.stream()
                .filter(tile -> tile.isOfType(Type.TARGET))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Return the set of each and every boxes present on the board.
     *
     * @return a list of Entity objects
     */
    List<Entity> boxes() {
        return this.ENTITIES.stream()
                .filter(entity -> entity.isOfType(Type.BOX))
                .collect(Collectors.toUnmodifiableList());
    }
    
    /**
     * Return the player.
     *
     * @return an Entity object
     */
    Entity player() {
        return this.ENTITIES.stream()
                .filter(entity -> entity.isOfType(Type.PLAYER))
                .findFirst().orElse(null);
    }

    /**
     * Add a vertical wall of the given size at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the wall
     */
    public void addVerticalWall(int x, int y, int size) {
        for (int i = y; i < y + size; i++) {
            Tile wall = Tile.newWall(x, y);
            this.TILES.add(wall);
        }
    }

    /**
     * Add a box on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addBox(int x, int y) {
        Entity box = Entity.newBox(x, y);
        this.ENTITIES.add(box);
    }

    /**
     * Add a target on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addTarget(int x, int y) {
        Tile target = Tile.newTarget(x, y);
        this.TILES.add(target);
    }

    /**
     * Set the player's position on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setPlayerPosition(int x, int y) {
        Entity player = Entity.newPlayer(x, y);
        this.ENTITIES.add(player);
    }

    /**
     * Draw the board on the screen.
     */
    void draw() {
        displayColumnNumbers();
        for (int i = 0; i < this.WIDTH; i++) {
            System.out.print("\n" + i);
            for (int j = 0; j < this.LENGTH; j++) {
                char character;
                var coord = new Coordinates(j, i);
                try {
                    SokobanElement element = findElement(coord);
                    character = element.character();
                } catch (ElementNotFoundException e) {
                    character = '.';
                }
                System.out.print(" " + character);
            }
        }
        System.out.print("\n");
    }
    
    SokobanElement findElement(Coordinates coord) throws ElementNotFoundException {
        SokobanElement element;
        try {
            element = findEntity(coord);
        } catch (ElementNotFoundException e) {
            element = findTile(coord);
        } return element;
    }
    
    SokobanElement findEntity(Coordinates coord) throws ElementNotFoundException {
        return this.ENTITIES.stream()
                .filter(entity -> entity.isAtPosition(coord))
                .findFirst().orElseThrow(() 
                        -> new ElementNotFoundException(coord));
    }
    
    SokobanElement findTile(Coordinates coord) throws ElementNotFoundException {
        return this.TILES.stream()
                .filter(tile -> tile.isAtPosition(coord))
                .findFirst().orElseThrow(() 
                        -> new ElementNotFoundException(coord));
    }

    /**
     * Display the column numbers on the board.
     */
    void displayColumnNumbers() {
        System.out.print(" ");
        for (int i = 0; i < this.LENGTH; i++) {
            System.out.print(" " + i);
        }
    }
}
