package roguetutorial;

/**
 * Created by avyatkin on 20/02/16.
 */
public class World {
    private Tile[][] tiles;
    private int width;
    private int height;

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public Tile tile(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }



    public World(Tile[][] tiless) {
        tiles = tiless;
        width = tiless.length;
        height = tiless[0].length;
    }
}

