package lighting;

import geometries.Intersectable;
import primitives.*;

import static primitives.Util.alignZero;

/**
 * class SpotLight represents a point light in a specific direction
 */
public class SpotLight extends PointLight {
    /**
     * direction of light
     */
    private final Vector direction;

    /**
     * constructor for Light
     *
     * @param intensity spotlight intensity
     * @param point     spotlight position
     * @param vector    spotlight direction vector (will be normalized)
     */
    public SpotLight(Color intensity, Point point, Vector vector) {
        super(intensity, point);
        direction = vector.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        double dp = alignZero(direction.dotProduct(getL(p)));
        return dp > 0 ? super.getIntensity(p).scale(dp) : Color.BLACK;
    }

    @Override
    public double getDistance(Intersectable.GeoPoint geoPoint) {
        return super.getDistance(geoPoint);
    }
}
