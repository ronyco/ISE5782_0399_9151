package geometries;

import primitives.*;


/**
 * the class plane represents a plane in the three dimension
 */
public class Plane implements Geometry{
    Point q0;
    Vector normal;

    /**
     * Constructor to initialize Plane with three number values
     * @param p1 first number value
     * @param p2 second number value
     * @param p3 third number value
     */
    public Plane(Point p1,Point p2, Point p3) {
        if(p1.equals(p2)||p1.equals(p3)||p2.equals(p3))
            throw new IllegalArgumentException("cannot initialise plane with identical points");
        try{
            normal = ((p1.subtract(p2)).crossProduct(p3.subtract(p1))).normalize();
        }
        catch (Exception e){
            throw new IllegalArgumentException("cannot initialise plane with points on the same line");
        }
        q0=p1;

    }

    /**
     * Constructor to initialize Plane with a Point and a vector normal to the plane
     * @param p point
     * @param v normal vector
     */
    public Plane(Point p,Vector v) {
        q0 = p;
        normal = v.normalize();
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    public Point getQ0() {
        return q0;
    }
    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
