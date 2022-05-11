package lighting;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class PointLight represents a light source as a point
 */
public class PointLight extends Light implements LightSource {
    /**
     * position of light source
     */
    private final Point position;

    private double kC = 1.0;
    private double kL = 0.0;
    private double kQ = 0.0;

    /**
     * constructor for PointLight
     *
     * @param intensity to initiate color
     * @param point     to initiate position
     */
    public PointLight(Color intensity, Point point) {
        super(intensity);
        this.position = point;
    }

    /**
     * setter for kC
     *
     * @param kC to initialize
     * @return PointLight object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for kL
     *
     * @param kL to initialize
     * @return PointLight object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for kQ
     *
     * @param kQ to initialize
     * @return PointLight object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double distance = position.distance(p);
        return intensity.reduce(kC + kL * distance + kQ * (distance * distance));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return position.distance(p);
    }
}
