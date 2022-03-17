package geometries;
import primitives.*;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * Class plane represents a plane in the three dimension
 */
public class Plane implements Geometry {
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
     *
     * @param p point
     * @param v vector (Not necessarily normalized)
     */
    public Plane(Point p, Vector v) {
        q0 = p;
        normal = v.normalize();
    }


    /***
     * Given a point, return normal to this point
     * @param p is point
     * @return normal vector to a specific Point
     */
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
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        //Denominator
        double nv = alignZero(n.dotProduct(v));
        //Ray direction cannot be parallel to plane orientation
        if(isZero(nv)) { return null; }

        Vector P0_QO = q0.subtract(P0);


        if(q0.equals(P0)) {
            return null;
        }

        //Numerator
        double nQMinusP0 = alignZero(n.dotProduct(P0_QO));
        //T should be superior to zero
        if(isZero(nQMinusP0)) {
            return null;
        }
        double t = alignZero(nQMinusP0/nv);

        // t should be > 0
        if(t <= 0)
        {
            return null;
        }
        //T superior to zero
        return List.of(ray.getPoint(t));
    }
}
