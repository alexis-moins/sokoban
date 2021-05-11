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

    private final ArrayList<String> board_;

    public FileBoardBuilder() {
        this.board_ = new ArrayList<>();
    }


    public static FileBoardBuilder open(String path) {
        var builder = new FileBoardBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                builder.board_.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Unable to open file under " + path);
        }
        return builder;
    }

    /**
     * Create and return a builder from the information from a file.
     *
     * @return a builder object
     * @throws InvalidCharacterException the builder couldn't be built
     */
    @Override
    public Board build() throws InvalidCharacterException {
        var builder = new TextBoardBuilder(this.board_.get(0));
        for (int i = 1; i < this.board_.size(); i++) {
            builder.append(this.board_.get(i));
        }
        return builder.build();
    }

}
