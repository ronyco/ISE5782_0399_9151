package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Triangle class represents two-dimensional Triangle in 3D Cartesian coordinate
 */
public class Triangle extends Polygon {
    /**
     * Triangle constructor that inialize a triangle using three point that passed by parameter
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
}
