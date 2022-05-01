package renderer;

import lighting.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;

/**
 * Ray Tracer Basic Class
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * constructor for RayTracerBasic
     *
     * @param scene of 3D object
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculate color of a point with ambient light
     *
     * @param geoPoint in the scene
     * @return Color of point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }

    /**
     * calculates effects on color
     *
     * @param gp  point
     * @param ray Ray
     * @return color
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material.kD, nl)
                        .add(calcSpecular(material.kS, l, n, nl, v, material.nShininess))));
            }
        }
        return color;
    }

    /**
     * calculates Specular effect
     *
     * @param ks         of material
     * @param l          normalized from light source
     * @param n          normal to the intersected geometry surface at the point
     * @param nl         double(n*l)
     * @param v          direction of the ray
     * @param nShininess of material of the intersected geometry
     * @return color of specular effect
     */
    private Double3 calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double vr = alignZero(v.dotProduct(r));
        if (vr >= 0) {
            return Double3.ZERO; // view from direction opposite to r vector
        }
        // [rs,gs,bs]ks(-v.R)^p
        return ks.scale(Math.pow(-vr, nShininess));
    }

    /**
     * calculates Diffusive effect
     *
     * @param kd diffusive attenuation factor of material
     * @param nl double(n*l)
     * @return color
     */
    private Double3 calcDiffusive(Double3 kd, double nl) {
        return kd.scale(Math.abs(nl));
    }
}
