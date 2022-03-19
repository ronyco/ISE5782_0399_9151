package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

    /**
     * Sphere constructor that initialize a sphere using a point that represent center of sphere and a radius
     */
    public Sphere(Point c, double r) {
        center = c;
        radius = r;
    }

    /***
     * Returns double that represent radius of sphere
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /***
     * Returns a point who is the center of sphere
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /***
     * Display some information about Sphere
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /***
     * Return normal to the sphere
     * @param p is point
     * @return new Vector given by the formula normalize(p-0)
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            return List.of(center.add(v.scale(radius)));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // When d superior or equal to radius, there are no intersections
        //Ray is above the circle
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        //Using formula we only takes t: when superior to zero
        if (t1 > 0 && t2 > 0) {
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
            Point P1 =ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
            Point P2 =ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
    }

}
