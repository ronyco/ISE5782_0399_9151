package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
public class Tube implements Geometry {
    Ray axisRay;
    double radius;

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
