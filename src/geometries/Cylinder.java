package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Cylinder class represents three-dimensional cylinder in 3D Cartesian coordinate. It's a finite tube
 * Inherit from Tube class
 */
public class Cylinder extends Tube {
    double height;

    /****
     * Cylinder constructor. Build a cylinder using a ray(Direction) and a radius
     * @param ray
     * @param rad
     */
    public Cylinder(Ray ray, double rad){super(ray, rad);}

    /*****
     * @return Height of the cylinder- It's a finite tube so there is a height
     */
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
