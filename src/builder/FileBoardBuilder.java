
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class creating a builder from a file.
 *
 * @author Alexis Moins
 */
class FileBoardBuilder implements BoardBuilder {

    private final ArrayList<String> info_;

    FileBoardBuilder() {
        this.info_ = new ArrayList<>();
    }

    
    public static FileBoardBuilder open(String path) {
        var builder = new FileBoardBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                builder.info_.add(scanner.nextLine());
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
     * @throws BuilderException the builder couldn't be built
     */
    @Override
    public Board build() throws InvalidCharacterException {
        var builder = new TextBoardBuilder(this.info_.get(0));
        for (int i = 1; i < this.info_.size(); i++) {
            builder.addRow(this.info_.get(i));
        }
        return builder.build();
    }

}
