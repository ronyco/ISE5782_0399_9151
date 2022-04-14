package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * abstract class Geometry implements Geometric objects and function
 */
public abstract class Geometry extends Intersectable{

    /**
     * emission color of Geometry
     */
    protected Color emission = Color.BLACK;

    /**
     * Material of Geometry
     */
    private Material material= new Material();

    /**
     * getter for Material
     * @return Material of Geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Setter for material of Geometry
     * @param material of Geometry
     * @return Geometry object
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

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
