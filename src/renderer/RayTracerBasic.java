package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections != null) {
            return calcColor((ray.findClosestGeoPoint(intersections)));
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }

    /**
     * Calculate color of a point with ambient light
     * @param geoPoint in the scene
     * @return Color of point
     */
    private Color calcColor(GeoPoint geoPoint) {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }
}
