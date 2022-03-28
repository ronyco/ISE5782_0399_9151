package elements;

import primitives.*;

public class AmbientLight {

    private Color intensity;

    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    public AmbientLight() {
        intensity= Color.BLACK;
    }

    public Color getIntensity() {
        return intensity;
    }
}
