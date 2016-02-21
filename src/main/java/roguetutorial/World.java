package roguetutorial;

import roguetutorial.creatures.Creature;

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

    public void dig(int x, int y) {
        if (tile(x,y).isDiggable())
            tiles[x][y] = Tile.FLOOR;
    }

    public void addAtEmptyNewLocation(Creature creature) {
        int x;
        int y;

        do {
            x = (int)(Math.random() * width);
            y = (int)(Math.random() * height);
        } while (!tile(x,y).isGround());

        creature.x = x;
        creature.y = y;
    }
}

