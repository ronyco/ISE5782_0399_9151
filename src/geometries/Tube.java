package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Tube class represents three-dimensional Tube in 3D Cartesian coordinate
 */
 public class Tube implements Geometry {
    protected final Ray axisRay;
    protected final double radius;

    /**
     * Tube constructor that represents a infinite cylinder using a ray and a radius
     */
    public Tube(Ray ray, double rad){
        axisRay=ray;
        radius=rad;
    }

    /***
     *@return radius of tube
     */
    public double getRadius() {
        return radius;
    }

    /***
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
}
