package lighting;

import primitives.*;

/**
 * class DirectionalLight represents a directional light source
 */
public class DirectionalLight extends Light implements LightSource{
    /**
     * direction of light
     */
    private final Vector direction;

    /**
     * Constructs directional light source with its intensity and direction of the light.
     * The direction vector will be normalized.
     * @param intensity to initiate color
     * @param vector to initiate direction
     */
    public DirectionalLight(Color intensity, Vector vector) {
        super(intensity);
        direction = vector.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}
