package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Tube class represents two-dimensional Tube in 3D Cartesian coordinate
 */
 public class Tube implements Geometry {
    Ray axisRay;
    double radius;

    /**
     * constructor
     */
    public Tube(){}

    public double getRadius() {
        return radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
