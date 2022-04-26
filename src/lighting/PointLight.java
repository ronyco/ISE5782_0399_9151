package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class PointLight represents a light source as a point
 */
public class PointLight extends Light implements LightSource{
    /**
     * position of light source
     */
    private Point position;

    private double kC = 1.0;
    private double  kL = 0.0;
    private double kQ = 0.0;

    /**
     * constructor for PointLight
     *
     * @param intensity to initiate color
     * @param point to initiate position
      */
    public PointLight(Color intensity, Point point) {
        super(intensity);
        this.position = point;
    }

    /**
     * setter for kC
     * @param kC to initialize
     * @return PointLight object
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return  this;
    }

    /**
     * setter for kL
     * @param kL to initialize
     * @return PointLight object
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return  this;
    }
    /**
     * setter for kQ
     * @param kQ to initialize
     * @return PointLight object
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return  this;
    }

    /**
     * helper function for getIntensity
     * @param p point
     * @return color
     */
    public Color getIntensityHelper(Point p) {
        double distance = position.distance(p);
        return getIntensity().reduce(kC + kL * distance + kQ * (distance*distance));
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensityHelper(p);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
