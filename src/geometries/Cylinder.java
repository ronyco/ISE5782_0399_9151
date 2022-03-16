package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents three-dimensional cylinder in 3D Cartesian coordinate. It's a finite tube
 * Inherit from Tube class
 */
public class Cylinder extends Tube {
    private final double height;

    /****
     * Cylinder constructor. Build a cylinder using a ray(Direction) and a radius
     * @param ray
     * @param rad
     */
    public Cylinder(Ray ray, double rad, double height ) {
        super(ray, rad);
        this.height = height;
    }

    /*****
     * @return Height of the cylinder- It's a finite tube so there is a height
     */
    public double getHeight() {
        return height;
    }

    /***
     * Display some information about cylinder
     * @return a string with info
     */
    @Override
    public String toString() {
        return super.toString() +
                "Cylinder{" +
                "height=" + height +
                '}';
    }

    /***
     * Return normal to cylinder for now null
     * @param p
     * @return for now null0
     */
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
