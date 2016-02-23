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

    public Point3D getBounds() {
        return bounds;
    }

    private Point3D bounds;

    public Tile getTile(Point3D point) {
        if (!point.withinBounds(bounds))
            return Tile.BOUNDS;
        else
            return tiles[point.x][point.y][point.z];
    }

    public void setTile(Point3D point, Tile newTile) {
        if (!point.withinBounds(bounds)) {
            System.out.println("Invalid tile coords: " + point);
            return;
        }
        else
            tiles[point.x][point.y][point.z] = newTile;
    }

    public Optional<Creature> creatureAt(Point3D point) {
        return creatures.stream().filter((c) -> c.coords.equals(point)).findFirst();
    }


    public World(Tile[][][] tiless) {
        tiles = tiless;

        bounds = new Point3D(tiless.length, tiless[0].length, tiless[0][0].length);
        creatures = new ArrayList<>();
    }

    public void dig(Point3D point) {
        if (getTile(point).isDiggable())
            setTile(point, Tile.FLOOR);
    }

    public void addAtEmptyLocation(Creature creature, int z) {
        Point3D buildPoint;
        do {
            int x = (int)(Math.random() * bounds.x);
            int y = (int)(Math.random() * bounds.y);
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

