package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


/***
 * Composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> intersections = new LinkedList<Intersectable>();

    /**
     * Constructor of geometries who takes some intersectables
     *
     * @param intersectables geometric objects
     */
    public Geometries(Intersectable... intersectables) {
        add(intersectables);
    }

    /**
     * Function that add intersectable to geometries
     *
     * @param intersectables with objects
     */
    public void add(Intersectable... intersectables) {
        if (intersectables.length != 0) Collections.addAll(intersections, intersectables);
    }

    @Override
    public Box setBoundBox() {
        double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY, minY = Double.POSITIVE_INFINITY,
                maxY = Double.NEGATIVE_INFINITY, minZ = Double.POSITIVE_INFINITY, maxZ = Double.NEGATIVE_INFINITY,
                x = 0, y = 0, z = 0;
        for (Intersectable shape : intersections) {
            if (shape instanceof Plane)
                return new Box(new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY),
                        new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
            Box box = shape.box;
            x = box.min.getX();
            y = box.min.getY();
            z = box.min.getZ();
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (z < minZ) minZ = z;
            x = box.max.getX();
            y = box.max.getY();
            z = box.max.getZ();
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
            if (z > maxZ) maxZ = z;
        }
        return new Box(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        if (boundingIntersection(ray)) {
            for (var item : intersections) {
                List<GeoPoint> itemList = item.findGeoIntersections(ray);
                if (itemList != null) {
                    if (result == null) //for the first time addition
                        result = new LinkedList<>(itemList);
                    else
                        result.addAll(itemList);
                }

            }
        }
        return result;
    }

    /**
     * calculate distance between boxes
     *
     * @param g1 first geometries
     * @param g2 second geometries
     * @return distance between geometries
     */
    public double getDistance(Geometries g1, Geometries g2) {
        Box box1 = g1.setBoundBox();
        Box box2 = g2.setBoundBox();
        // gives bottom-left point
        // of intersection rectangle
        double x5 = Math.max(box1.min.getX(), box2.min.getX());
        double y5 = Math.max(box1.min.getY(), box2.min.getY());
        // gives top-right point
        // of intersection rectangle
        double x6 = Math.min(box1.max.getX(), box2.max.getX());
        double y6 = Math.min(box1.max.getY(), box2.max.getY());
        // no intersection
        if (!(x5 > x6 || y5 > y6))
            return 0;

        double dis1 = box1.min.distance(box2.min);
        double dis2 = box1.min.distance(box2.max);
        double dis3 = box1.max.distance(box2.min);
        double dis4 = box1.max.distance(box2.max);
        double minDistance = dis1;

        if (dis2 < minDistance) minDistance = dis2;
        if (dis3 < minDistance) minDistance = dis3;
        if (dis4 < minDistance) minDistance = dis4;
        return minDistance;
    }


    /**
     * automatic bounding volume hierarchy
     */
    public void autoBVH(double maxDistance) {
        if (intersections.size() >= 2) {
            Geometries min1, min2;
            int index1 = 0, index2 = 1;
            double minDistance = Double.POSITIVE_INFINITY, checkDistance;
            for (int i = 0; i < intersections.size(); i++) {
                min1 = new Geometries(intersections.get(i));
                for (int j = 0; j < intersections.size(); j++) {
                    min2 = new Geometries(intersections.get(j));
                    checkDistance = getDistance(min1, min2);
                    if (checkDistance < minDistance) {
                        minDistance = checkDistance;
                        index1 = i;
                        index2 = j;
                    }
                }
            }
            min1 = new Geometries(intersections.remove(index1));
            min1.createBox();
            min2 = new Geometries(intersections.remove(index2));
            min2.createBox();
            if (getDistance(min1, min2) < maxDistance) {
                min1.intersections.addAll(min2.intersections);
                min1.createBox();
                intersections.add(min1);
            } else {
                Geometries node = new Geometries(min1, min2);
                node.createBox();
                intersections.add(node);
            }

            autoBVH(maxDistance);
        }
    }

}


