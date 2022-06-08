package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * Triangle constructor that initialize a triangle using three point that passed by parameter
     *
     * @param p1 First Point value
     * @param p2 Second Point value
     * @param p3 Third Point value
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = plane.findGeoIntersections(ray);
        //If there is no intersections with plane return null
        if (result == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(1).subtract(p0);
        Vector v2 = vertices.get(0).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double sign1 = alignZero(v.dotProduct(v1.crossProduct(v2)));
        if (sign1 == 0) return null;
        double sign2 = alignZero(v.dotProduct(v2.crossProduct(v3)));
        if (sign1 * sign2 <= 0) return null;
        double sign3 = alignZero(v.dotProduct(v3.crossProduct(v1)));
        if (sign1 * sign3 <= 0) return null;

        return List.of(new GeoPoint(this, result.get(0).point));

    }

}
