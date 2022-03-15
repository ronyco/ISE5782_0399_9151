package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube class represents three-dimensional Tube in 3D Cartesian coordinate
 */
 public class Tube implements Geometry {
    protected final Ray axisRay;
    protected final double radius;

    /**
     * Tube constructor that represents an infinite cylinder using a ray and a radius
     */
    public Tube(Ray ray, double rad){
        axisRay=ray;
        radius=rad;
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
        double t= (axisRay.getDir()).dotProduct(p.subtract(axisRay.getP0()));
        Point O = axisRay.getP0().add(axisRay.getDir().scale(t));
        return (p.subtract(O)).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
