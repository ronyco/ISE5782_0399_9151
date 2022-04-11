package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * abstract class Geometry implements Geometric objects and function
 */
public abstract class Geometry extends Intersectable{

    protected Color emission = Color.BLACK;

    /***
     * Return normal to different geometries
     * @param p is point
     * @return new vector who is a normal vector to specific point passed by parameter
     */
    public abstract Vector getNormal(Point p);

    public Color getEmission() {
        return emission;
    }
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
