class Entity {

    private final char CHARACTER;

    private final Coordinates COORDINATES;

    Entity(int x, int y, char character) {
       this.COORDINATES = new Coordinates(x, y);
       this.CHARACTER = character;
    }
    
    Coordinates coordinates() {
        return this.COORDINATES;
    }

    /**
     * Create and return a new box entity.
     *
     * @return a box entity.
     */
    public static Entity box(int x, int y) {
        return new Entity(x, y, 'C');
    }

    /**
     * Create and return a new player entity.
     *
     * @return a player entity.
     */
    public static Entity player(int x, int y) {
        return new Entity(x, y, 'P');
    }

}
