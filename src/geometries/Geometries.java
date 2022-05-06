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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (var item : intersections) {
            List<GeoPoint> itemList = item.findGeoIntersections(ray,maxDistance);
            if (itemList != null) {
                if (result == null) //for the first time addition
                    result = new LinkedList<>(itemList);
                else
                    result.addAll(itemList);
            }
        }
        return result;
    }


}

