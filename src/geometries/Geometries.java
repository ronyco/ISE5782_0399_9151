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
    private final List<Intersectable> intersections = new LinkedList<Intersectable>();

    public Geometries() { }

    public Geometries(Intersectable... intersectables) {
        add(intersectables);
    }

    public void add(Intersectable... intersectables) {
        Collections.addAll(intersections, intersectables);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (var item : intersections) {
            List<Point> itemList = item.findIntersections(ray);
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

