package geometries;

import primitives.*;

import java.util.List;

/***
 * Interface for finding intersections points
 */
public interface Intersectable {
    /***
     * FindIntersections function returns list of intersections using class Point
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersections {@link Point}
     */
    public List<Point> findIntersections(Ray ray);
}
