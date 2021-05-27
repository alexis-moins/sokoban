package game;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import elements.Tile;
import elements.Entity;
import elements.BoardElement;

import utils.Type;
import utils.Coordinates;

/**
 * Class representing the board.
 *
 * @author Alexis Moins
 */
public final class Board {

    /**
     * The width of the board.
     */
    private final int WIDTH;

    /**
     * The length of the board.
     */
    private final int LENGTH;

    /**
     * The description of the board.
     */
    private final String DESCRIPTION;

    /**
     * The player.
     */
    private final Entity PLAYER;

    /**
     * The list of the tiles (the walls and the targets) on the board.
     */
    private final ArrayList<Tile> TILES;

    /**
     * The list of the entities (the boxes) on the board.
     */
    private final ArrayList<Entity> ENTITIES;

    /**
     * Parameterised constructor creating a new board.
     *
     * @param description the description of the board
     * @param length the length of the board
     * @param width the width of the board
     */
    public Board(final String description, final int length, final int width) {
        this.DESCRIPTION = description;
        this.WIDTH = width;
        this.LENGTH = length;
        this.PLAYER = Entity.newPlayer(0, 0);

        this.TILES = new ArrayList<>();
        this.ENTITIES = new ArrayList<>();
    }

    /**
     * Return the player.
     *
     * @return an Entity object
     */
    Entity player() {
        return this.PLAYER;
    }

    /**
     * Return the list of each and every targets present on the board.
     *
     * @return a list (unmodifiable) of Tile objects
     */
    List<Tile> targets() {
        return this.TILES.stream()
            .filter(tile -> tile.isOfType(Type.TARGET))
            .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Return the list of each and every boxes present on the board.
     *
     * @return a list (unmodifiable) of Entity objects
     */
    List<Entity> boxes() {
        return Collections.unmodifiableList(this.ENTITIES);
    }

    /**
     * Add a vertical wall of the given size at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the wall
     */
    public void addVerticalWall(final int x, final int y, final int size) {
        for (int i = y; i < y + size; i++) {
            Tile wall = Tile.newWall(x, y);
            this.TILES.add(wall);
        }
    }
    
    /**
     * Add an horizontal wall of the given size at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param size the size of the wall
     */
    public void addHorizontalWall(final int x, final int y, final int size) {
        for (int i = x; i < x + size; i++) {
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
    public void addBox(final int x, final int y) {
        Entity box = Entity.newBox(x, y);
        this.ENTITIES.add(box);
    }

    /**
     * Add a target on the board at the given coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addTarget(final int x, final int y) {
        Tile target = Tile.newTarget(x, y);
        this.TILES.add(target);
    }

    /**
     * Set the player's position on the board at the given coordinates.
     *
     * @param coord the destination coordinates
     */
    public void movePlayer(final Coordinates coord) {
        this.PLAYER.setPosition(coord);
    }

    /**
     * Draw the board on the screen.
     */
    public void draw() {
        displayColumnNumbers();
        for (int i = 0; i < this.WIDTH; i++) {
            System.out.print("\n" + i);
            for (int j = 0; j < this.LENGTH; j++) {
                Coordinates coord = new Coordinates(j, i);
                char character = getCorrectCharacter(coord);
                System.out.print(" " + character);
            }
        }
        System.out.print("\n");
    }

    /**
     * Display the column numbers on the board.
     */
    void displayColumnNumbers() {
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < this.LENGTH; i++) {
            System.out.print(" " + i);
        }
    }

    /**
     * Return the character representing the element found at the given
     * coordinates.
     *
     * @param coord the considered coordinates
     * @return a character
     */
    private char getCorrectCharacter(final Coordinates coord) {
        if (this.PLAYER.isAtPosition(coord))
            return this.PLAYER.character();
        BoardElement element = findElement(coord);
        return (element == null) ? '.' : element.character();
    }

    /**
     * Return the element found on the board at the given coordinates. Return
     * null if no element was found.
     *
     * @param coord the considered coordinates
     * @return a BoardElement object
     */
    BoardElement findElement(final Coordinates coord) {
        BoardElement entity;
        entity = findEntity(coord);
        return (entity == null) ? findTile(coord) : entity;
    }

    /**
     * Return the tile found on the board at the given coordinates. Return null
     * if no tiles were found.
     *
     * @param coord the considered coordinates
     * @return a Tile object
     */
    Tile findTile(final Coordinates coord) {
        return this.TILES.stream()
            .filter(tile -> tile.isAtPosition(coord))
            .findFirst().orElse(null);
    }

    /**
     * Return the entity found on the board at the given coordinates. Return null
     * if no tile was found.
     *
     * @param coord the considered coordinates
     * @return a Entity object
     */
    Entity findEntity(final Coordinates coord) {
        return this.ENTITIES.stream()
            .filter(tile -> tile.isAtPosition(coord))
            .findFirst().orElse(null);
    }

    /**
     * Return the representation of the board as a string.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return "(" + this.LENGTH + "x" + this.WIDTH + ")\n" + this.DESCRIPTION;
    }

}
