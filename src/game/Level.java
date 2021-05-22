package game;

import elements.Entity;
import elements.BoardElement;
import utils.Direction;
import utils.Type;
import utils.Utils;
import utils.Coordinates;

import exceptions.PlayerLeavesException;
import exceptions.InvalidDirectionException;

/**
 * Class representing a level in the game.
 *
 * @author Alexis Moins
 */
public class Level {

    /**
     * The playable board of the level.
     */
    private final Board BOARD;

    /**
     * True if the board of the level is completed.
     */
    private boolean isFinished;

    /**
     * The array of valid moves the player can perform on the board.
     */
    private static final char[] expectedMoves = {'u', 'd', 'l', 'r'};

    /**
     * Parameterised constructor creating a new Level object.
     *
     * @param board the board of the level
     */
    public Level(Board board) {
        this.BOARD = board;
        this.isFinished = false;
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
        while (!isFinished) {
            Utils.clearScreen();
            this.BOARD.draw();
            String message = "\nWhat do you want to do : ";
            String move = Utils.askUser(message).toLowerCase();
            playerTurn(move);
        }
    }

    private void playerTurn(final String move) {
        try {
            tryMove(move);
            playMove(move);
            if (boardIsCompleted())
                displayWinMessage();
        } catch (PlayerLeavesException ex) {
            System.out.println("- " + ex.getMessage());
            this.isFinished = true;
        } catch (InvalidDirectionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayWinMessage() {
        this.BOARD.draw();
        System.out.println("\nYou completed the board, congratulations !");
        this.isFinished = true;
    }

    /**
     * Return true if the given string is considered a valid (series of) play on the board.
     *
     * @param choice the considered string
     * @throws PlayerLeavesException the player quits the game
     * @throws InvalidDirectionException the specified direction is not valid
     * @return a boolean
     */
    private boolean tryMove(final String moves) throws PlayerLeavesException, InvalidDirectionException {
        if ("/q".equals(moves) || "/quit".equals(moves))
            throw new PlayerLeavesException();
        char[] movesArray = moves.toCharArray();
        for (char move : movesArray) {
            if (!moveIsValid(move))
                throw new InvalidDirectionException(move);
        }
        return true;
    }

    private boolean moveIsValid(char choice) {
        for (char move : expectedMoves) {
            if (move == choice)
                return true;
        }
        return false;
    }

    /**
     * Play the selected move on the current board.
     *
     * @param move the user selected move
     */
    private void playMove(final String move) {
        Utils.clearScreen();
        for (char m : move.toCharArray()) {
            String c = String.valueOf(m);
            playSingleMove(c);
        }
    }

    private void playSingleMove(final String move) {
        Direction dir = Direction.correspondingTo(move);
        Coordinates coord = this.BOARD.player().coordinates().next(dir);
        BoardElement destination = this.BOARD.findElement(coord);
        if (destination == null || destination.isOfType(Type.TARGET)) {
            this.BOARD.movePlayer(coord);
        } else if (destination.isOfType(Type.BOX) && boxCanBeMoved(coord, dir)) {
            moveElementInDirection(coord, dir);
            this.BOARD.movePlayer(coord);
        }
    }

    private boolean boxCanBeMoved(final Coordinates coord, final Direction dir) {
        Coordinates newCoord = coord.next(dir);
        BoardElement element = this.BOARD.findElement(coord);
        if (element == null)
            return true;
        else if (element.isOfType(Type.BOX))
            return boxCanBeMoved(newCoord, dir);
        return !element.hasCollisions();
    }

    private void moveElementInDirection(final Coordinates coord, final Direction dir) {
        Entity entity = this.BOARD.findEntity(coord);
        Coordinates newCoord = coord.next(dir);
        if (entity != null) {
            moveElementInDirection(newCoord, dir);
            entity.setPosition(newCoord);
        }
    }

}