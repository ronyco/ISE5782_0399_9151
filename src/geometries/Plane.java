package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * Class plane represents a plane in the three dimension
 */
public class Plane extends Geometry {
    private final Point q0;
    private final Vector normal;

    /**
     * Plane constructor that initialize a plane using three points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        normal = ((p1.subtract(p2)).crossProduct(p3.subtract(p1))).normalize();
        q0 = p1;

    }

    /**
     * Constructor that initialize a plane using a point and a vector
     * Save vector after that it will be normalized
     * @param p point
     * @param v vector (Not necessarily normalized)
     */
    public Plane(Point p, Vector v) {
        q0 = p;
        normal = v.normalize();
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /***
     * Returns point q0 that plane is based on it
     * @return q0
     */
    public Point getQ0() {
        return q0;
    }

    /***
     * Returns normal that plane is based on it
     * @return normal vector
     */
    public Vector getNormal() {
        return normal;
    }

    /***
     * Display some information about plane
     */
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //In the formula we have to find P, but first we have to find t
        //So that P = P0 + t.v | t > 0
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector p0Q0;
        try {
            p0Q0 = q0.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            //Ray is on the plane itself
            return null;
        }

        //Denominator
        double nv =normal.dotProduct(v);
        //Ray direction cannot be parallel to plane orientation
        if (isZero(nv))
            return null;

        //Numerator
        double t = alignZero(normal.dotProduct(p0Q0) / nv);
        return t > 0 ? List.of(new GeoPoint(this, ray.getPoint(t))) : null;
        //If inferior to zero, no intersection
    }
}
