package roguetutorial.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by avyatkin on 23/02/16.
 */
public class Point2D {
    public int x;
    public int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        return (result * prime + x) * prime + y;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Point3D) {
            Point3D point = (Point3D) obj;
            return this.x == point.x && this.y == point.y;
        } else {
            return false;
        }
    }

    public List<Point2D> neighbors8() {
        List<Point2D> points = new ArrayList<>();
        for (int ox = -1; ox < 2; ox++)
            for (int oy = -1; oy < 2; oy++)
                if (ox == 0 && oy == 0)
                    continue;
                else
                    points.add(new Point2D(x + ox, y + oy));

        Collections.shuffle(points);
        return points;
    }
}


