package builders;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

import game.Board;
import utils.Utils;

/**
 * Class creating a builder from a file.
 *
 * @author Alexis Moins
 */
public final class FileBoardBuilder implements BoardBuilder {

    /**
     * The rows of the file used to build the board.
     */
    private final ArrayList<String> ROWS;

    /**
     * Parameterised constructor creating a new FileBoardBuilder object.
     */
    public FileBoardBuilder() {
        this.ROWS = new ArrayList<>();
    }

    /**
     * Return a builder created from a text file.
     *
     * @param path the path leading to the text file
     * @return a FileBoardBuilder object
     */
    public static FileBoardBuilder deserialise(String path) {
        var builder = new FileBoardBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine())
                builder.ROWS.add(scanner.nextLine());
            return builder;
        } catch (FileNotFoundException ex) {
            Utils.errorMessage("Unable to open file under " + path);
            return null;
        }
    }

    /**
     * Return a TextBoardBuilder object created using the current object's
     * information.
     *
     * @return a TextBoardBuilder object
     */
    public TextBoardBuilder convertToTextBuilder() {
        var builder = new TextBoardBuilder(this.ROWS.get(0));
        for (int i = 1; i < this.ROWS.size(); i++) {
            builder.append(this.ROWS.get(i));
        }
        return builder;
    }

    /**
     * Return a board created with the current builder information.
     *
     * @return a Board object
     */
    @Override
    public Board build() {
        TextBoardBuilder builder = convertToTextBuilder();
        return builder.build();
    }

}
