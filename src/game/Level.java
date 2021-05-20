package game;

import exceptions.PlayerLeavesException;

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
     * The array of valid moves the player can perform on the board.
     */
    private static final char[] expectedMoves = {'U', 'D', 'L', 'R'};

    /**
     * Parameterised constructor creating a new Level object.
     *
     * @param board the board of the level
     */
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
            Utils.clearScreen();
            this.BOARD.draw();
            String move = Utils.askUser("\nWhat do you want to do : ").toUpperCase();
            try {
                tryMove(move);
                playMove(move);
                if (boardIsCompleted()) {
                    this.BOARD.draw();
                    System.out.println("\nYou completed the board, congratulations !");
                    finished = true;
                }
            } catch (PlayerLeavesException e) {
                System.err.println("* " + e.getMessage());
                finished = true;
            }
        }
    }

    /**
     * Return true if the given string is considered a valid (series of) play on the board.
     *
     * @param choice the considered string
     * @throws PlayerLeavesException the player quits the game
     * @return a boolean
     */
    private boolean tryMove(final String choice) throws PlayerLeavesException {
        if ("/Q".equals(choice) || "/QUIT".equals(choice))
            throw new PlayerLeavesException();

        int i = 0;
        boolean isValid  = true; 
        char[] choices = choice.toCharArray();
        while (isValid && i < choices.length) {
            if (!moveIsValid(choices[i]))
                isValid = false;
            i++;
        }
        return isValid;
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
            Utils.clearScreen();
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
        return element.hasCollisions();
    }

    private void moveElementInDirection(final Coordinates coord, final Direction dir) {
        Coordinates newCoord = coord.next(dir);
        Entity entity = this.BOARD.findEntity(newCoord);
        if (entity != null) {
            moveElementInDirection(newCoord, dir);
            entity.setPosition(newCoord.next(dir));
        } else {
            this.BOARD.findEntity(coord).setPosition(newCoord);
        }
    }

}
