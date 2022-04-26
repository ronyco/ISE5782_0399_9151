package lighting;

import primitives.*;

/**
 * class SpotLight represents a point light in a specific direction
 */
public class SpotLight extends PointLight{
    /**
     * direction of light
     */
    private Vector direction;
    /**
     * constructor for Light
     *
     * @param intensity to initiate color
     * @param point to initiate position
     * @param vector to initiate
     */
    public SpotLight(Color intensity, Point point, Vector vector) {
        super(intensity, point);
        direction = vector.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double dp = direction.dotProduct(getL(p));
        return dp>0 ? getIntensityHelper(p).scale(dp) : getIntensityHelper(p).scale(0);
    }
}
