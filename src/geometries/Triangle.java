package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * Triangle Constructor with 3 points
     * @param p1 First Point value
     * @param p2 Second Point value
     * @param p3 Third Point value
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
