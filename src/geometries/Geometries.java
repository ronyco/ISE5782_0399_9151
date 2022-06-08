package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collection;
import java.util.Collections;
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
}

