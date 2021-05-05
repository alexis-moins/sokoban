Entity {

    private final char CHARACTER;

    private final Coordinates COORDINATES;

    Entity(int x, int y, char character) {
       this.COORDINATES = new Coordinates(x, y);
       this.CHARACTER = character;
    }

    public void setCharacter(char character) {
        this.CHARACTER = character;
    }

    public static Entity box(int x, int x) {
        return new Entity(x, y, 'C');
    }

    public static Entity player(int x, int x) {
        return new Entity(x, y, 'P');
    }

}
