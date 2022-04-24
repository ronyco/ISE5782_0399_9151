package lighting;

import primitives.*;

/**
 * interface for lights source that illuminates in the scene
 */
public interface LightSource {
    /**
     * get color of point
     * @param p point
     * @return color
     */
    public Color getIntensity(Point p);

    /**
     * Get vector of light thanks to point
     * @param p which represent a point
     * @return a vector
     */
    public Vector getL(Point p);
}
