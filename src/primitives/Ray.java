package primitives;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Ray class represents a ray in three dimension space, meaning a point with a one-sided direction
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * movement of beginning of rays
     */
    public static final double DELTA = 0.1;


    /**
     * Constructor to initialize Ray with point and normal vector
     *
     * @param p0  for point
     * @param dir for normal vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructor to initialize Ray with point direction and normal
     *
     * @param p0  Begin of the point
     * @param n   normal to the vector (must not be orthogonal to the dir vector)
     * @param dir represent direction of vector (must be normalized)
     */
    public Ray(Point p0, Vector dir, Vector n) {
        this.dir = dir;
        // make sure the normal and the direction are not orthogonal
        double nv = alignZero(n.dotProduct(dir));
        // move the head of the vector in the right direction
        this.p0 = p0.add(n.scale(nv > 0 ? DELTA : -DELTA));
    }


    /***
     * Returns the point P0 of ray
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    /***
     * Returns a vector who represent direction of the ray
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }

    /***
     * Get Point at specific distance in the ray direction
     * @param t distance for reaching new Point
     * @return new {@link Point} who represent point { P | P = P0 + t.v }
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * find the closest point to beginning of ray
     *
     * @param points list of point on ray
     * @return closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * find the closest point on geometric to beginning of ray
     *
     * @param listOfPoints list of point on ray
     * @return closest point on geometric
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listOfPoints) {
        if (listOfPoints == null)
            return null;

        GeoPoint closestPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (var pt : listOfPoints) {
            double d = p0.distance(pt.point);
            if (d < minDistance) {
                minDistance = d;
                closestPoint = pt;
            }
        }

        return closestPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * create beam of rays in direction of ray at virtual square put in given distance
     *
     * @param deltaLength square that rays will go through will be placed at distance
     * @return list of beam of rays
     */
    public List<Ray> createBeamOfRays(double deltaLength) {
        List<Ray> beam = new LinkedList<Ray>();
        beam.add(this);
        if(alignZero(deltaLength)==0)
            return beam;
        Vector sidePivot = dir.crossProduct(new Vector(0, 0, 1)).normalize();
        Vector upPivot = dir.crossProduct(sidePivot).normalize();

        int i, j;
        //first quarter
        for (i = 1; i <= 3; i++)
            for (j = 3; j <= 3; j++)
                beam.add(new Ray(p0, dir.add(sidePivot.scale(deltaLength * i).add(upPivot.scale(deltaLength * j)))));
        //second quarter
        sidePivot = sidePivot.scale(-1);
        for (i = 1; i <= 3; i++)
            for (j = 3; j <= 3; j++)
                beam.add(new Ray(p0, dir.add(sidePivot.scale(deltaLength * i).add(upPivot.scale(deltaLength * j)))));
        //third quarter
        upPivot = upPivot.scale(-1);
        for (i = 1; i <= 3; i++)
            for (j = 3; j <= 3; j++)
                beam.add(new Ray(p0, dir.add(sidePivot.scale(deltaLength * i).add(upPivot.scale(deltaLength * j)))));
        //fourth quarter
        sidePivot = sidePivot.scale(-1);
        for (i = 1; i <= 3; i++)
            for (j = 3; j <= 3; j++)
                beam.add(new Ray(p0, dir.add(sidePivot.scale(deltaLength * i).add(upPivot.scale(deltaLength * j)))));

        return beam;
    }
}
