package geometries;

import primitives.*;

import java.util.*;

/**
 * abstract class for finding intersections points
 */
public abstract class Intersectable {

    /**
     * box for intersectable
     */
    public Box box = null;

    /**
     * box feature
     * @return instance
     */
    public Intersectable createBox() {
        box = setBoundBox();
        return this;
    }

    /**
     * internal class to create a box
     */
    public static class Box {
        public Point min, max;

        public Box(Point min, Point max) {
            this.min = min;
            this.max = max;
        }

        /**
         * check if intersect with box
         *
         * @param ray for intersection
         * @return true in intersect false if not
         */
        private boolean privateBoundingIntersection(Ray ray) {
            Point po = ray.getP0();
            double dfX = 1.0 / ray.getDir().getX();
            double dfY = 1.0 / ray.getDir().getY();
            double dfZ = 1.0 / ray.getDir().getZ();

            double t1 = (min.getX() - po.getX()) * dfX;
            double t2 = (max.getX() - po.getX()) * dfX;
            double t3 = (min.getX() - po.getX()) * dfY;
            double t4 = (max.getX() - po.getX()) * dfY;
            double t5 = (min.getX() - po.getX()) * dfZ;
            double t6 = (max.getX() - po.getX()) * dfZ;

            double tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
            double tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

            if (tmax < 0) return false;
            if (tmin > tmax) return false;
            return true;
        }
    }

    /**
     * check if intersect with box
     *
     * @param ray for intersection
     * @return true in intersect false if not
     */
    public boolean boundingIntersection(Ray ray) {
        if (box != null)
            return box.privateBoundingIntersection(ray);
        return true;
    }


    /**
     * for each intersectable create bound box
     */
    abstract public Box setBoundBox();


    /**
     * class GeoPoint to help with findIntersections
     */
    public static class GeoPoint {
        /***
         * Geometry field in helper class
         */
        public Geometry geometry;
        /**
         * Point filed in helper class
         */
        public Point point;

        /**
         * helper class GeoPoint constructor
         *
         * @param geometry object of intersection
         * @param point    of intersection
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    /**
     * FindIntersections function calculates all the intersection points between a ray and the geometry
     *
     * @param ray {@link Ray} to intersect with the geometry
     * @return list of intersections {@link Point}
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * FindGeoIntersections function finds intersections with a specific geometry
     *
     * @param ray from camera passing in parameter
     * @return List of intersections with geometry
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (!boundingIntersection(ray)) return null;
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
