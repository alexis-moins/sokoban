
import java.util.HashSet;

/**
 *
 * @author Alexis Moins
 */
class Board {

    private final int LENGTH;

    private final int WIDTH;

    private Entity player;

    private final HashSet<Tile> TILES;

    private final HashSet<Entity> BOXES;

    Board(int length, int width) {
        this.LENGTH = length;
        this.WIDTH = width;
        this.TILES = new HashSet<Tile>();
    }

    void addHorizontalWall(int x, int y, int size) {
        for (int i = x; i < size; i++) {
            var wall = Tile.wall(x, y);
            this.TILES.add(wall);
        }
    }

    void addVerticalWall(int x, int y, int size) {
        for (int i = y; i < size; i++) {
            var wall = Tile.wall(x, y);
            this.TILES.add(wall);
        }
    }

    void setPosition(int x, int y) {
        this.player = Entity.player(x, y);
    }

    void addBox(int x, int y) {
        var box = Entity.box(x, y);
        this.BOXES.add(box);
    }

    void display() {
        for (int i = 0; i < this.WIDTH; i++) {
            for (int j = 0; j < this.LENGTH; j++) {
                var tile = this.TILES.get(i);
                if (tile.isCrossedBy(this.player)) {
                    System.out.print('P');
                } else if (tile.contains(this.BOXES)) {
                    System.out.print('#');
                } else {
                    System.out.print(tile.character());
                }
            }
            System.out.println(" ");
        }
    }

}
