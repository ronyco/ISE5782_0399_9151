package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {
    double height;

    public Cylinder(){}

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return   super.toString() +
                "Cylinder{" +
                "height=" + height +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
