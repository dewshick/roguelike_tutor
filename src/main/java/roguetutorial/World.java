package roguetutorial;

import roguetutorial.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by avyatkin on 20/02/16.
 */
public class World {
    private Tile[][] tiles;

    public List<Creature> getCreatures() { return creatures; }

    private List<Creature> creatures;
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

    public Optional<Creature> creatureAt(int x, int y) {
        return creatures.stream().filter((c) -> c.x == x && c.y == y).findFirst();
    }


    public World(Tile[][] tiless) {
        tiles = tiless;
        width = tiless.length;
        height = tiless[0].length;
        creatures = new ArrayList<Creature>();
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
        } while (!tile(x,y).isGround() && !creatureAt(x, y).isPresent());

        creature.x = x;
        creature.y = y;
        creatures.add(creature);
    }

    public void remove(Creature enemy) {
        creatures.remove(enemy);
    }

    public void update() {
        ArrayList<Creature> toUpdate = new ArrayList<>(creatures);
        for (Creature c :  toUpdate) { c.update(); }
    }
}

