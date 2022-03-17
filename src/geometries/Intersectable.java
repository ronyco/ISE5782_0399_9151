package geometries;

import primitives.*;

import java.util.List;

/***
 * Interface for finding intersections points
 */
public interface Intersectable {
    /***
     *
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersections {@link Point}
     */
    public List<Point> findIntersections(Ray ray);
}
