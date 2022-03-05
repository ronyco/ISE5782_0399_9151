package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize Triangle with three points
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
