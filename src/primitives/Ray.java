package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Ray class represents a ray in three dimension space, meaning a point with a one-sided direction
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

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
        if (o == null || !(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
