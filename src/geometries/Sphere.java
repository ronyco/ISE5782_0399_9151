package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Sphere class represents two-dimensional Sphere in 3D Cartesian coordinate
 */
public class Sphere implements Geometry{
    Point center;
    double radius;

    /**
     * constructor
     */
    public Sphere(Point c, double r){
        center=c;
        radius=r;
    }

    public double getRadius() {
        return radius;
    }

    public Point getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
