package game;

import exceptions.ElementNotFoundException;
import java.util.Scanner;

import exceptions.SokobanException;

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
    private boolean isCompleted() {
        for (Tile target : this.BOARD.targets()) {
            try {
                var coord = target.coordinates();
                SokobanElement validBox = this.BOARD.findEntity(coord);
            } catch (ElementNotFoundException e) {
                return false;
            }
        } return true;
    }

    private String getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next()
            .trim().toUpperCase();
    }

    public void start() {
        while (!isCompleted()) {
            this.BOARD.draw();
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
        var coordinates = this.BOARD.player()
                .coordinates().next(direction);
        try {
            var destination = this.BOARD.findTile(coordinates);
        
        } catch (SokobanException e) {
            System.err.println("/!\\ Out of bound destination");
        }
    }

}
