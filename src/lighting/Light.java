package lighting;

import primitives.Color;

/**
 * Light abstract class that illuminates scene
 */
abstract class Light {
    /**
     * Light intensity as Color
     */
    protected final Color intensity;

    /**
     * constructor for Light
     * @param intensity to initiate object
     */
    protected Light(Color intensity){
        this.intensity = intensity;
    }

    /**
     * getter for light intensity
     * @return intensity of light
     */
    public Color getIntensity() {
        return intensity;
    }

}
