package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Interface Geometry implements Geometric objects and function
 */
public interface Geometry extends Intersectable{
    /***
     * Return normal to different geometries
     * @param p is point
     * @return new vector who is a normal vector to specific point passed by parameter
     */
    public Vector getNormal(Point p);
}
