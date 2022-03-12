package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Cylinder class represents two-dimensional Cylinder in 3D Cartesian coordinate
 */
public class Cylinder extends Tube {
    double height;

    /**
     * constructor
     */
    public Cylinder(Ray ray, double rad){super(ray, rad);}

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
