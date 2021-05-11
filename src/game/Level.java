package game;

import java.util.Scanner;

import exceptions.SokobanException;

/**
 * Class representing a level in the game.
 *
 * @author Alexis Moins
 */
public class Level {

    private final Board board;

    public Level(Board board) {
        this.board = board;
    }

    /**
     * Return true if all the targets of the level are covered with boxes on 
     * the board.
     *
     * @return a boolean
     */
    private boolean isCompleted() {
        for (var coord : this.board.targets()) {
            if (!this.board.boxes().contains(coord))
                return false;
        } return true;
    }

    private String getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next()
            .trim().toUpperCase();
    }

    public void start() {
        while (!isCompleted()) {
            this.board.draw();
            String move = getPlayerMove();
            try {
                playMove(move);
            } catch (SokobanException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * 
     *
     * @param move the user selected move
     * @throws SokobanException the selected move is not valid
     */
    private void playMove(String move) throws SokobanException {
        var direction = Direction.correspondingTo(move);
        Coordinates position = this.board.playerPosition().next(direction);
        char tile = this.board.tileCharacter(position);
        if (tile == '.') {
            this.board.setPlayerPosition(position.x(), position.y());
        } else if (tile == 'C' && this.board.crateCanBeMoved()) {
            this.board.setPlayerPosition(position.x(), position.y());
        }
    }

}
