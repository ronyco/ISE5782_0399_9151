package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon {
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
