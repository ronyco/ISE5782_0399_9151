package lighting;

import primitives.*;

/**
 * class DirectionalLight represents a directional light source
 */
public class DirectionalLight extends Light implements LightSource{
    /**
     * direction of light
     */
    private Vector direction;

    /**
     * constructor for DirectionalLight
     * @param intensity to initiate color
     * @param vector to initiate direction
     */
    public DirectionalLight(Color intensity, Vector vector) {
        super(intensity);
        direction = vector;
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
