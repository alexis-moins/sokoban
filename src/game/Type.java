package game;

/**
 * Enumeration listing all the type of elements that can be created.
 *
 * @author Alexis Moins
 */
enum Type {

    BOX,
    WALL,
    PLAYER,
    TARGET;

    /**
     * Return the character representing visually the element of the
     * correspondin type on the board.
     *
     * @return a character
     */
    public char toChar() {
        switch (this) {
            case BOX:
                return 'C';
            case WALL:
                return '#';
            case PLAYER:
                return 'P';
            case TARGET:
                return 'x';
        }
        return '!';
    }
    
    /**
     * Return true if an element of the considered type can be crossed by the
     * player on the board.
     *
     * @return a boolean
     */
    public boolean canBeCrossed() {
        switch (this) {
            case BOX:
                return false;
            case WALL:
                return false;
            case PLAYER:
                return true;
            case TARGET:
                return true;
        }
        return false;
    }

}
