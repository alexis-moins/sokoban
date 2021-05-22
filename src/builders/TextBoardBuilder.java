package builders;

import java.util.ArrayList;

import utils.Type;
import game.Board;
import utils.Coordinates;

/**
 * Class creating a board from strings.
 *
 * @author Alexis Moins
 */
public final class TextBoardBuilder implements BoardBuilder {

    /**
     * The name of the board
     */
    private final String NAME;

    /**
     * The width of the board
     */
    private int WIDTH;

    /**
     * The length of the board
     */
    private int LENGTH;

    /**
     * The list of the rows of the board
     */
    private final ArrayList<String> ROWS;

    /**
     * Parameterised constructor creating a new TextBoardBuilder object.
     *
     * @param name the name of the board
     */
    public TextBoardBuilder(String name) {
        this.WIDTH = 0;
        this.LENGTH = 0;
        this.NAME = name;
        this.ROWS = new ArrayList<>();
    }

    /**
     * Return the name of the board.
     *
     * @return a string
     */
    public String name() {
        return this.NAME;
    }

    /**
     * Return the width of the board (number of rows).
     *
     * @return an integer
     */
    public int width() {
        return this.WIDTH;
    }

    /**
     * Return the length of the board.
     *
     * @return an integer
     */
    public int length() {
        return this.LENGTH;
    }

    /**
     * Return the row at the given index.
     *
     * @param i the index of the desired row
     * @return a string
     */
    public String getRow(int i) {
        return this.ROWS.get(i);
    }

    /**
     * Add the given string to the board builder.
     *
     * @param row a string to be added
     */
    public void append(final String row) {
        this.ROWS.add(row);
        this.WIDTH++;
        if (this.LENGTH == 0)
            this.LENGTH = row.length();
    }

    /**
     * Return a board created with the current builder informations.
     *
     * @return a Board object
     */
    @Override
    public Board build() {
        var board = new Board(this.NAME, this.LENGTH, this.WIDTH);
        for (int i = 0; i < this.ROWS.size(); i++) {
            String row = this.ROWS.get(i);
            deserialise(row, i, board);
        }
        return board;
    }

    /**
     * Deserialise the given row of the board.
     *
     * @param row the considered row
     * @param y the number of the considered row
     * @param board the board of the game
     */
    private void deserialise(String row, int y, Board board) {
        char[] tiles = row.toCharArray();
        for (int x = 0; x < tiles.length; x++) {
            char tile = tiles[x];
            if (tile == Type.PLAYER.character())
                board.movePlayer(new Coordinates(x, y));
            else if (tile == Type.WALL.character())
                board.addVerticalWall(x, y, 1);
            else if (tile == Type.TARGET.character())
                board.addTarget(x, y);
            else if (tile == Type.BOX.character())
                board.addBox(x, y);
        }
    }

}
