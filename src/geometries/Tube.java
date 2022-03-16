package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class represents three-dimensional Tube in 3D Cartesian coordinate
 */
public class Tube implements Geometry {
    protected final Ray axisRay;
    protected final double radius;

    /**
     * Tube constructor that represents an infinite cylinder using a ray and a radius
     */
    public Tube(Ray ray, double rad) {
        axisRay = ray;
        radius = rad;
    }

    /***
     * Get function that return radius of tube
     *@return radius of tube
     */
    public double getRadius() {
        return radius;
    }

    /***
     * Get function that return axisRay of Tube
     @return axisRay of tube
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /***
     * Returns a normal vector to specific point passing by parameter
     * @param p
     */
    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        double t = dir.dotProduct(p.subtract(p0));
        Point o = isZero(t) ? p0 : p0.add(dir.scale(t));

        return (p.subtract(o)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
