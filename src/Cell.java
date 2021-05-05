Tile {

    private final char CHARACTER;

    private boolean CAN_BE_CROSSED;

    private final Coordinates COORDINATES;

    Tile(int x, int y) {
        this.CHARACTER = '.';
        this.CAN_BE_CROSSED = true;
        this.COORDINATES = new Coordinates(x, y);
    }

    public static wall(int x, int y) {
        var wall = new Tile(x, y);
        wall.setCharacter('#');
        wall.canBeCrossed(false);
        return wall;
    }

    /**
     * Accessor for the CHARACTER attriute.
     *
     * @return the current tile's character.
     */
    public char character() {
        return this.CHARACTER;
    }

    public void setCharacter(char character) {
        this.CHARACTER = character;
    }

    public Coordinates coordinates() {
        return this.COORDINATES;
    }

    public boolean canBeCrossed() {
        return this.CAN_BE_CROSSED;
    }

    public boolean canBeCrossed(boolean condition) {
        this.CAN_BE_CROSSED = condition;
    }

}
