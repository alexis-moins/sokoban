package game;

import exceptions.PlayerLeavesException;

/**
 * Class representing a level in the game.
 *
 * @author Alexis Moins
 */
public class Level {

    private final Board BOARD;
    
    private static final char[] expectedMoves = {'U', 'D', 'L', 'R'};

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
                if (boardIsCompleted()) {
                    this.BOARD.draw();
                    System.out.println("\nLe niveau est terminé, félicitations !");
                    finished = true;
                }
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
    private boolean tryMove(final String choice) throws PlayerLeavesException {
        if ("/q".equals(choice) || "/quit".equals(choice))
                throw new PlayerLeavesException();

        boolean choiceIsValid = true; int i = 0;
        char[] choices = choice.toCharArray();
        while (choiceIsValid && i < choices.length) {
            if (choices[i])
        }
        return choiceIsValid;
    }
    
    private boolean moveIsValid(char move) {
        for () {
            if (move == )
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
        Direction direction = Direction.correspondingTo(move);
        Coordinates coordinates = this.BOARD.player().coordinates().next(direction);
        BoardElement destination = this.BOARD.findElement(coordinates);
        if (destination == null) {
            this.BOARD.movePlayer(coordinates);
        } else if (destination.isOfType(Type.BOX) && boxCanBeMoved(coordinates, direction)) {
            moveElementInDirection(coordinates, direction);
            this.BOARD.movePlayer(coordinates);
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