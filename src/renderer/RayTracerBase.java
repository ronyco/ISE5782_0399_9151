package renderer;

import primitives.*;
import scene.Scene;

/**
 * Ray tracer Base class
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Base constructor that takes scene in parameter
     * @param scene of 3D object
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract function which trace a ray
     * @param ray in scene
     * @return Color instance
     */
    public abstract Color traceRay(Ray ray);
}
