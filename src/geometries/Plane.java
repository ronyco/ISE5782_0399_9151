package geometries;

import primitives.*;

import java.util.List;


/**
 * Class plane represents a plane in the three dimension
 */
public class Plane implements Geometry{
    private final Point q0;
    private final Vector normal;

    /**
     * Plane constructor that initialize a plane using three points
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Plane(Point p1,Point p2, Point p3) {
        if(p1.equals(p2)||p1.equals(p3)||p2.equals(p3))
            throw new IllegalArgumentException("can't initialise plane with identical points");
        try{
            normal = ((p1.subtract(p2)).crossProduct(p3.subtract(p1))).normalize();
        }
        catch (Exception e){
            throw new IllegalArgumentException("can't initialise plane with points on the same line");
        }
        q0=p1;

    }

    /**
     * Constructor that initialize a plane using a point and a vector
     * Save vector after that it will be normalized
     * @param p point
     * @param v vector (Not necessarily normalized)
     */
    public Plane(Point p,Vector v) {
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
        return null;
    }
}
