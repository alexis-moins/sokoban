
import java.util.Scanner;

/**
 * Class representing a level in the game.
 *
 * @author Alexis Moins
 */
class Level {

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

    private void displayObjective() {
        System.out.println("\nYou will be presented with a level. Your objective     # = a wall");
        System.out.println("is to thoroughly place all the wooden crated of the    P = your character");
        System.out.println("level on the specified targets.                        C = wooden crates");
        System.out.println("                                                       x = the targets");
        System.out.println("                                                       . = the ground");

    }

    private String getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next()
            .trim().toUpperCase();
    }

    public void start() {
        displayObjective();
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
        if (tile == '.')
            this.board.setPlayerPosition(position.x(), position.y());
    }

}
