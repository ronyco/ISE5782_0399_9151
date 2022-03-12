package geometries;

import primitives.Point;
import primitives.Vector;

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

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }
}
