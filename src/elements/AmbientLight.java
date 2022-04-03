package elements;

import primitives.*;

/**
 * Ambient light for all the objects in the scene
 */
public class AmbientLight {

    private final Color intensity; //Light intensity as Color

    /**
     * Constructor with 2 parameters
     * @param iA Basic light intensity
     * @param kA Factor of intensity
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Default Constructor which return black color
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * getter for ambient light intensity
     * @return intensity of ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
