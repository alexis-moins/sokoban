/**
 * Class representing the four possible directions on the board.
 *
 * @author Alexis Moins
 */
enum Direction {

    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * Return the movement on the x axis when going in that direction.
     *
     * @return an integer
     */
    public int xMovement() {
        int direction = 0;
        switch (this) {
            case EAST:
                direction = 1;
                break;
            case WEST:
                direction = -1;
                break;
        }
        return direction;
    }

    /**
     * Return the movement on the y axis when going in that direction.
     *
     * @return an integer
     */
    public int yMovement() {
        int direction = 0;
        switch (this) {
            case NORTH:
                direction = -1;
                break;
            case SOUTH:
                direction = 1;
                break;
        }
        return direction;
    }

    static Direction correspondingTo(String move) throws InvalidDirectionException {
        Direction direction = null;
        switch (move) {
            case "U":
                direction = Direction.NORTH;
                break;
            case "D":
                direction = Direction.SOUTH;
                break;
            case "R":
                direction = Direction.EAST;
                break;
            case "L":
                direction = Direction.WEST;
                break;
            default:
                throw new InvalidDirectionException(move);
        }
        return direction;
    }

}
