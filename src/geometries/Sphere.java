package geometries;

import primitives.Point;
import primitives.Vector;
public class Sphere implements Geometry{
    Point center;
    double radius;

    public Sphere(){}

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
