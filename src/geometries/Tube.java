package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class represents three-dimensional Tube in 3D Cartesian coordinate
 */
public class Tube extends Geometry {
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

    @Override
    public Vector getNormal(Point p) {
        double t = axisRay.getDir().dotProduct(p.subtract(axisRay.getP0()));
        return (p.subtract(axisRay.getPoint(t))).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
