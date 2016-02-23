package roguetutorial.world;

import roguetutorial.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by avyatkin on 20/02/16.
 */
public class World {
    private Tile[][][] tiles;

    public List<Creature> getCreatures() { return creatures; }

    private List<Creature> creatures;
    private int width;
    private int height;
    private int depth;

    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public int getDepth() { return depth; }

    public Tile getTile(Point3D point) {
        int x = point.x;
        int y = point.y;
        int z = point.z;
        if (x >= width || x < 0 || y >= height || y < 0 || z >= depth || z < 0)
            return Tile.BOUNDS;
        else
            return tiles[x][y][z];
    }

    public void setTile(Point3D point, Tile newTile) {
        int x = point.x;
        int y = point.y;
        int z = point.z;
        if (x >= width || x < 0 || y >= height || y < 0 || z >= depth || z < 0)
            return;
        else
            tiles[x][y][z] = newTile;
    }

    public Optional<Creature> creatureAt(Point3D point) {
        return creatures.stream().filter((c) -> c.coords == point).findFirst();
    }


    public World(Tile[][][] tiless) {
        tiles = tiless;
        width = tiless.length;
        height = tiless[0].length;
        creatures = new ArrayList<Creature>();
    }

    public void dig(Point3D point) {
        if (getTile(point).isDiggable())
            setTile(point, Tile.FLOOR);
    }

    public void addAtEmptyLocation(Creature creature, int z) {
        Point3D buildPoint;

        do {
            int x = (int)(Math.random() * width);
            int y = (int)(Math.random() * height);
            buildPoint = new Point3D(x, y, z);
        } while (!getTile(buildPoint).isGround() && !creatureAt(buildPoint).isPresent());

        creature.coords = buildPoint;
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

