package lighting;

import primitives.*;

/**
 * interface for light sources
 */
public interface LightSource {
    /**
     * get color of point
     * @param p point
     * @return color
     */
    public Color getIntensity(Point p);

    /**
     *
     * @param p
     * @return
     */
    public Vector getL(Point p);
}
