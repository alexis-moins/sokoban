package game;

import java.util.Arrays;

import exceptions.PlayerLeavesException;
import exceptions.ElementNotFoundException;

/**
 * Class representing a level in the game.
 *
 * @author Alexis Moins
 */
public class Level {

    private final Board BOARD;

    public Level(Board board) {
        this.BOARD = board;
    }

    /**
     * Return true if all the targets of the level are covered with boxes on 
     * the board.
     *
     * @return a boolean
     */
    private boolean boardIsCompleted() {
        for (var target : this.BOARD.targets()) {
            boolean found = false; int i = 0;
            Coordinates coord = target.coordinates();
            while (!found && i < this.BOARD.boxes().size()) {
                if (this.BOARD.boxes().get(i).isAtPosition(coord))
                    found = true;
                i++;
            }

            if (!found)
                return false;
        } return true;
    }

    public void start() {
        boolean finished = false;
        while (!finished) {
            this.BOARD.draw();
            String move = Utils.getInput().toUpperCase();
            try {
                tryMove(move);
                playMove(move);
                finished = boardIsCompleted();
            } catch (PlayerLeavesException e) {
                System.err.println("* " + e.getMessage());
                finished = true;
            }
        }
    }

    /**
     * Return true if the given string is considered valid on the board.
     *
     * @param choice the considered string
     * @throws PlayerLeavesException the player quits the game
     * @return a boolean
     */
    private boolean tryMove(String choice) throws PlayerLeavesException {
        String[] moves = {"U", "D", "L", "R"};
        if (Arrays.asList(moves).contains(choice))
            return true;
        if ("/q".equals(choice) || "/quit".equals(choice))
            throw new PlayerLeavesException();
        return false;
    }

    /**
     * Play the selected move on the current board.
     *
     * @param move the user selected move
     */
    private void playMove(final String move) {
        var direction = Direction.correspondingTo(move);
        var coordinates = this.BOARD.player().coordinates().next(direction);
        try {
            BoardElement destination = this.BOARD.findElement(coordinates);
            if (destination.isOfType(Type.BOX) && crateCanBeMoved(coordinates, direction)) {
                moveElementInDirection(coordinates, direction);
                this.BOARD.movePlayer(coordinates);
            }
        } catch (ElementNotFoundException e) {
            this.BOARD.movePlayer(coordinates);
        }
    }

    private boolean crateCanBeMoved(final Coordinates coord, final Direction dir) {
        try {
            var newCoord = coord.next(dir);
            var element = this.BOARD.findElement(coord);
            if (element.isOfType(Type.BOX))
                return crateCanBeMoved(newCoord, dir);
            return element.hasCollisions();
        } catch (ElementNotFoundException e) {
            return true;
        }
    }

    private void moveElementInDirection(final Coordinates coord, final Direction dir) {
        try {
            var newCoord = coord.next(dir);
            var element = this.BOARD.findEntity(coord);
            moveElementInDirection(newCoord, dir);
            element.setPosition(newCoord.next(dir));
        } catch (ElementNotFoundException e) {}
    }

}
