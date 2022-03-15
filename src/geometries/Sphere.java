package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

    /**
     * Sphere constructor that initialize a sphere using a point that represent center of sphere and a radius
     */
    public Sphere(Point c, double r) {
        center = c;
        radius = r;
    }

    /***
     * Returns double that represent radius of sphere
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /***
     * Returns a point who is the center of sphere
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /***
     * Display some information about Sphere
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /***
     * Return normal to the sphere
     * @param p is point
     * @return new Vector given by the formula normalize(p-0)
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
