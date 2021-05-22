package utils;

/**
 * Class representing the coordinates on the board.
 *
 * @author Alexis Moins.
 */
public final class Coordinates {

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
     * 
     * @param x the position on the x axis
     * @param y the position on the y axis
     */
    public Coordinates(final int x, final int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Return the coordinates next to the current one in the given direction.
     *
     * @param direction the considered direction
     * @return new Coordinates object
     */
    public Coordinates next(final Direction direction) {
        return new Coordinates(this.X + direction.xTranslation(),
                this.Y + direction.yTranslation());
    }

    /**
     * Return the position on the x axis of the current coordinates.
     * 
     * @return an integer
     */
    public int x() {
        return this.X;
    }

    /**
     * Return the position on the y axis of the current coordinates.
     * 
     * @return an integer
     */
    public int y() {
        return this.Y;
    }

    /**
     * Return the integer hashed value of the given value.
     * 
     * @return an integer
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.X;
        hash = 37 * hash + this.Y;
        return hash;
    }

    /**
     * Return true if the current object is equals to the given object 
     * (overrided method).
     * 
     * @param obj the considered object
     * @return a boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Coordinates other = (Coordinates) obj;
        if (this.X != other.X)
            return false;

        return this.Y == other.Y;
    }

}
