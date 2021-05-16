package builder;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import game.Board;
import exceptions.InvalidCharacterException;

/**
 * Class creating a board from strings.
 *
 * @author Alexis Moins
 */
public class TextBoardBuilder implements BoardBuilder {
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
    TextBoardBuilder(String name) {
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
    void append(String row) {
        this.ROWS.add(row);
        this.WIDTH++;
        if (this.LENGTH == 0)
            this.LENGTH = row.length();
    }

    /**
     * Return a board created with the current builder informations.
     *
     * @return a board object
     * @throws InvalidCharacterException an invalid character prevents the 
     * board from being built
     */
    @Override
    public Board build() throws InvalidCharacterException {
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
    private void deserialise(String row, int y, Board board) throws InvalidCharacterException {
        var tiles = row.split("");
        for (int x = 0; x < tiles.length; x++) {
            var tile = tiles[x];
            switch (tile) {
                case "P":
                    board.setPlayerPosition(x, y);
                    break;
                case "#":
                    board.addVerticalWall(x, y, 1);
                    break;
                case "x":
                    board.addTarget(x, y);
                    break;
                case "C":
                    board.addBox(x, y);
                    break;
                case ".":
                    break;
                default:
                    throw new InvalidCharacterException(tile);
            }
        }
    }

}
