package geometries;

import primitives.*;

import java.util.*;

/**
 * abstract class for finding intersections points
 */
public abstract class Intersectable {
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
         * @param geometry object of intersection
         * @param point of intersection
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
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
     * @param ray from camera passing in parameter
     * @return List of intersections with geometry
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
