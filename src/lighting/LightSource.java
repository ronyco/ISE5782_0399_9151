package lighting;

import geometries.Intersectable;
import primitives.*;

/**
 * interface for lights source that illuminates in the scene
 */
public interface LightSource {
    /**
     * Calculate intensity of light at a point
     * @param p enlightened point
     * @return light intensity at the point
     */
    public Color getIntensity(Point p);

    /**
     * Get <u><b>normalized</b></u> vector from light to point
     * @param p which represent a point
     * @return a vector
     */
    public Vector getL(Point p);

    /**
     * Calculate distance between light source and point
     * @param geoPoint in scene
     * @return distance
     */
    public double getDistance(Intersectable.GeoPoint geoPoint);
}
