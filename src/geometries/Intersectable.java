package geometries;

import primitives.*;

import java.util.List;

/**
 * Interface for finding intersections points
 */
public interface Intersectable {
    /**
     * FindIntersections function calculates all the intersection points between a ray and the geometry
     * @param ray {@link Ray} to intersect with the geometry
     * @return list of intersections {@link Point}
     */
    public List<Point> findIntersections(Ray ray);
}
