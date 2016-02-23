package roguetutorial.world;

import java.util.*;

/**
 * Created by avyatkin on 20/02/16.
 */
public class WorldBuilder {
    private Point3D size;
    private Tile[][][] tiles;
    private int[][][] regions;

    public WorldBuilder(Point3D size) {
        this.size = size;
        this.tiles = new Tile[size.x][size.y][size.z];
    }

    public World build() { return new World(tiles); }

    public WorldBuilder makeCaves() {
        return randomizeTiles().smooth(8).createRegions().connectRegions();
    }

    private WorldBuilder randomizeTiles() {
        for(int z = 0; z < size.z; z++)
            for(int x = 0; x < size.x; x++)
                for(int y = 0; y < size.y; y++)
                    tiles[x][y][z] = Math.random() > 0.5 ? Tile.FLOOR : Tile.WALL;

        return this;
    }

    private WorldBuilder smooth(int times) {
        Tile[][][] tiles2 = new Tile[size.x][size.y][size.z];
        for (int time = 0; time < times; time++)
            for(int z = 0; z < size.z; z++) {
                for (int x = 0; x < size.x; x++) {
                    for (int y = 0; y < size.y; y++) {
                        int floors = 0;
                        int rocks = 0;
                        for (int ox = -1; ox < 2; ox++) {
                            for (int oy = -1; oy < 2; oy++) {
                                if (x + ox < 0 || x + ox >= size.x || y + oy < 0 || y + oy >= size.y)
                                    continue;
                                else if (tiles[x + ox][y + oy][z] == Tile.FLOOR)
                                    floors++;
                                else
                                    rocks++;

                            }
                        }
                        tiles2[x][y][z] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
                    }
                }
                tiles = tiles2;
            }
        return this;
    }

    private WorldBuilder createRegions() {
        regions = new int[size.x][size.y][size.z];
        int nextRegion = 0;
        for (int z = 0; z < size.z; z++)
            for (int x = 0; x < size.x; x++)
                for (int y = 0; y < size.y; y++)
                    if (tiles[x][y][z] != Tile.WALL && regions[x][y][z] == 0) {
                        int size = fillRegion(nextRegion++, x, y, z);
                        if (size < 25)
                            removeRegion(nextRegion - 1, z);
                    }

        return this;
    }

    private void removeRegion(int region, int z) {
        for (int x = 0; x < size.x; x++)
            for (int y = 0; y < size.x; y++)
                if (regions[x][y][z] == region) {
                    regions[x][y][z] = 0;
                    tiles[x][y][z] = Tile.WALL;
                }
    }

    private int fillRegion(int region, int x, int y, int z) {
        int size = 1;
        ArrayList<Point3D> open = new ArrayList<>();
        open.add(new Point3D(x,y,z));
        while (!open.isEmpty()) {
            Point3D p = open.remove(0);

            for (Point3D nb : p.neighbors8()) {
                if (regions[nb.x][nb.y][nb.z] > 0 || tiles[nb.x][nb.y][nb.z] == Tile.WALL)
                    continue;
                size++;
                regions[nb.x][nb.y][nb.z] = region;
                open.add(nb);
            }
        }
        return size;
    }

    public WorldBuilder connectRegions() {
        for (int z = 0; z < size.z-1; z++) {
            connectRegionsDown(z);
        }
        return this;
    }

    private void connectRegionsDown(int z) {
        Set<String> connected = new HashSet<>();
        for (int x = 0; x < size.x; x++)
            for(int y = 0; y < size.y; y++) {
                String region = regions[x][y][z] + "," + regions[x][y][z + 1];
                if (tiles[x][y][z] == Tile.FLOOR &&
                        tiles[x][y][z + 1] == Tile.FLOOR &&
                        !connected.contains(region)) {
                    connected.add(region);
                    connectRegionsDown(z, regions[x][y][z], regions[x][y][z + 1]);
                }
            }
    }

    private void connectRegionsDown(int z, int r1, int r2) {
        List<Point3D> candidates = findRegionOverlaps(z, r1, r2);

        int stairs = 0;
        do {
            Point3D p = candidates.remove(0);
            tiles[p.x][p.y][p.z] = Tile.STAIRS_DOWN;
            tiles[p.x][p.y][p.z] = Tile.STAIRS_UP;
            stairs++;
        } while (candidates.size() / stairs > 250);
    }

    private List<Point3D> findRegionOverlaps(int z, int r1, int r2) {
        ArrayList<Point3D> candidates = new ArrayList<>();
        for(int x = 0; x < size.x; x++)
            for(int y = 0; y < size.y; y++)
                if (tiles[x][y][z] == Tile.FLOOR &&
                        tiles[x][y][z+1] == Tile.FLOOR &&
                        regions[x][y][z] == r1 &&
                        regions[x][y][z+1] == r2)
                    candidates.add(new Point3D(x,y,z));

        Collections.shuffle(candidates);
        return candidates;
    }


}

