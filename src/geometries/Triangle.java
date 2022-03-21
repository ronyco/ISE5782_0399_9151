package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * Triangle constructor that initialize a triangle using three point that passed by parameter
     * @param p1 First Point value
     * @param p2 Second Point value
     * @param p3 Third Point value
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    /***
     * Display some information about the triangle
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /***
     * Returns normal vector, who is the same as normal vector of the plane to the point passing by parameter
     * @param point
     * @return normal vector of the plane to the specific point passed by parameter
     */
    @Override
    public Vector getNormal(Point point) {return super.getNormal(point);}

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> result = plane.findIntersections(ray);

        //If there is no intersections with plane return null
        if (result == null) {
            return result;
        }

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(1).subtract(P0);
        Vector v2 = vertices.get(0).subtract(P0);
        Vector v3 = vertices.get(2).subtract(P0);

        double sign1 = alignZero(v.dotProduct(v1.crossProduct(v2)));
        double sign2 = alignZero(v.dotProduct(v2.crossProduct(v3)));
        double sign3 = alignZero(v.dotProduct(v3.crossProduct(v1)));

        if (isZero(sign1) || isZero(sign2) || isZero(sign3)) {
            return null;
        }

        boolean positive = sign1 > 0;
        if (positive != (sign2 > 0) || positive != (sign3 > 0)) {
            return null;
        }

        return result;

    }
}
