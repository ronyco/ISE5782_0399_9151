package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface Geometry implements Geometric objects and function
 */
public interface Geometry {
    public Vector getNormal(Point p);
}
