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
public class Geometries implements Intersectable {
    private final List<Intersectable> _intersectables;

    public Geometries() {
        _intersectables = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... intersectables) {
        _intersectables = new LinkedList<Intersectable>();
        Collections.addAll(_intersectables, intersectables);
    }

    public void add(Intersectable... intersectables) {
        Collections.addAll(_intersectables, intersectables);
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        //TODO comment here
        List<Point> geoIntersections = null;
        for (Intersectable geometry : _intersectables) {
            geoIntersections = geometry.findIntersections(ray);
        }
        if (geoIntersections != null) {
            if (result == null) {
                result = new LinkedList<>();
            }
            result.addAll(geoIntersections);
        }
        return result;
    }
}
