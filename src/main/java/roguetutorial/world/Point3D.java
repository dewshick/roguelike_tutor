package roguetutorial.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by avyatkin on 22/02/16.
 */
public class Point3D {
    public int x;
    public int y;
    public int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D plus(Point3D point) {
        return new Point3D(x + point.x, y + point.y, z + point.z);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        return ((result * prime + x) * prime + y) * prime + z;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Point3D) {
            Point3D point = (Point3D)obj;
            return this.x == point.x && this.y == point.y && this.z == point.z;
        } else {
            return false;
        }
    }

    public List<Point3D> neighbors8(Point3D bounds) {
        List<Point3D> points = new ArrayList<>();
        for (int ox = -1; ox < 2; ox++)
            for (int oy = -1; oy < 2; oy++) {
                Point3D pointToAdd = new Point3D(x + ox, y + oy, z);
                if (ox == 0 && oy == 0)
                    continue;
                else if (!pointToAdd.withinBounds(bounds))
                    continue;
                else
                    points.add(pointToAdd);
            }
        Collections.shuffle(points);
        return points;
    }

    public boolean withinBounds(Point3D bounds) {
        return !(x < 0 || y < 0 || z < 0 || x >= bounds.x || y >= bounds.y || z >= bounds.z);
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
