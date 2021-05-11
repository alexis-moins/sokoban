
import java.util.ArrayList;

/**
 * Class creating a board from strings.
 *
 * @author Alexis Moins
 */
class TextBoardBuilder implements BoardBuilder {

    private final String NAME;

    private int rowNumber;

    private int columnNumber;

    private int length_;

    private final ArrayList<String> rows;

    TextBoardBuilder(String name) {
        this.NAME = name;
        this.rowNumber = 0;
        this.rows = new ArrayList<>();
    }

    /**
     * Add the given string to the board builder.
     *
     * @param row a string to be added
     */
    void addRow(String row) {
        this.rows.add(row);
        this.rowNumber++;
        if (this.columnNumber == 0)
            this.columnNumber = row.length();
    }

    /**
     * Return a board created from the 
     *
     * @return a board object
     * @throws InvalidCharacterException an invalid character prevents the 
     * board from being built
     */
    @Override
    public Board build() throws InvalidCharacterException {
        var board = new Board(this.NAME, this.columnNumber, this.rowNumber);
        for (int i = 0; i < this.rows.size(); i++) {
            String row = this.rows.get(i);
            deserialise(row, i, board);
        }
        return board;
    }

    private void deserialise(String row, int y, Board board) 
            throws InvalidCharacterException {
            var tiles = row.split("");
            for (int x = 0; x < tiles.length; x++) {
                var tile = tiles[x];
                switch (tile) {
                    case "P":
                        board.setPlayerPosition(x, y);
                        break;
                    case "#":
                        board.addWall(x, y, 1, Direction.EAST);
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
