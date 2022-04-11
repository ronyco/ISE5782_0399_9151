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

    public Geometries() { }

    public Geometries(Intersectable... intersectables) {
        add(intersectables);
    }

    public void add(Intersectable... intersectables) {
        Collections.addAll(intersections, intersectables);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        for (var item : intersections) {
            List<GeoPoint> itemList = item.findGeoIntersectionsHelper(ray);
            if(itemList!= null){
                if(result== null)//for the first time addition
                {
                    result= new LinkedList<>();
                }
                result.addAll(itemList);
            }
        }
        return result;
    }

}

