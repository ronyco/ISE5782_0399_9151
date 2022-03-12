package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Interface Geometry implements Geometric objects and function
 */
public interface Geometry {
    public Vector getNormal(Point p);
}
