package builder;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

import game.Board;
import exceptions.InvalidCharacterException;

/**
 * Class creating a builder from a file.
 *
 * @author Alexis Moins
 */
public class FileBoardBuilder implements BoardBuilder {

    private final ArrayList<String> BOARD;

    public FileBoardBuilder() {
        this.BOARD = new ArrayList<>();
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
            while (scanner.hasNextLine()) {
                builder.BOARD.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file under " + path);
        }
        return builder;
    }

    public TextBoardBuilder convertToTextBuilder() {
        var builder = new TextBoardBuilder(this.BOARD.get(0));
        for (int i = 1; i < this.BOARD.size(); i++) {
            builder.append(this.BOARD.get(i));
        }
        return builder;
    }

    /**
     * Create and return a board created with the current builder.
     *
     * @return a builder object
     * @throws InvalidCharacterException the builder couldn't be built
     */
    @Override
    public Board build() throws InvalidCharacterException {
        var builder = convertToTextBuilder();
        return builder.build();
    }

}
