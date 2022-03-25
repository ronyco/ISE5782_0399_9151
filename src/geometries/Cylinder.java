package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    public Cylinder(Ray ray, double rad, double height) {
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

    @Override
    public Vector getNormal(Point p) {
        Point o = axisRay.getP0();
        Vector v = axisRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(p.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return p.subtract(o).normalize();
    }
}
