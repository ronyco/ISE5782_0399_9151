package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 *Ray Tracer Basic Class
 */
public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections != null) {
            return calcColor(ray.findClosestPoint(intersections));
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }

    /**
     * Calculate color of a point with ambient light
     * @param point in the scene
     * @return Color of point
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
