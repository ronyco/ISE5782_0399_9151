package lighting;

import primitives.*;

/**
 * Ambient light for all the objects in the scene
 */
public class AmbientLight extends Light {

    /**
     * Constructor with 2 parameters
     * @param iA Basic light intensity
     * @param kA Factor of intensity
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Default Constructor which return black color
     */
    public AmbientLight() {
        super(Color.BLACK);
    }


}
