package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class represents three-dimensional Sphere in 3D Cartesian coordinate
 */
public class Sphere extends Geometry {
    private final Point center;
    private final double radius;
    private final double radius2;

    /**
     * Sphere constructor that initialize a sphere using a point that represent center of sphere and a radius
     */
    public Sphere(Point c, double r) {
        center = c;
        radius = r;
        radius2 = r * r;
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


    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }


    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector u;
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }
        double tm = alignZero(v.dotProduct(u));
        double d2 = alignZero(u.lengthSquared() - tm * tm);
        double th2 = radius2 - d2;

        // When d superior or equal to radius, there are no intersections
        //Ray is above the circle
        if (alignZero(th2) <= 0)
            return null;

        double th = Math.sqrt(th2);
        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null; // both will be behind the ray

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2))) :
                List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));

    }

}
