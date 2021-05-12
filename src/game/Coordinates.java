package game;

/**
 * Class representing the coordinates on the board.
 *
 * @author Alexis Moins.
 */
public class Coordinates {
    
    /**
     * The position on the x axis.
     */
    private final int X;

    /**
     * The position on the y axis.
     */
    private final int Y;

    /**
     * Parameterised constructor creating a new Coordinates object.
     */
    Coordinates(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Return the coordinates next to the current one in the given direction.
     *
     * @return new coordinates
     */
    Coordinates next(Direction direction) {
        return new Coordinates(this.X + direction.xMovement(),
                this.Y + direction.yMovement());
    }

    int x() {
        return this.X;
    }

    int y() {
        return this.Y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.X;
        hash = 37 * hash + this.Y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return true;
    }
    
}
