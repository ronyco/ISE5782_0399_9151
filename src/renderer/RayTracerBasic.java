package renderer;

import lighting.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.*;

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
            return calcColor(ray.findClosestGeoPoint(intersections),ray);
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }

    /**
     * Calculate color of a point with ambient light
     * @param geoPoint in the scene
     * @return Color of point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }


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
                color = color.add(iL.scale(calcDiffusive(material.kD, nl)),
                        iL.scale(calcSpecular(material.kS, l, n, nl, v, material.nShininess)));
            }
        }
        return color;
    }

    private Double3 calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector V, int nShininess) {
        if (isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero for scaling the normal vector");
        }
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double vr = alignZero(V.dotProduct(r));
        if (vr >= 0) {
            return new Double3(0); // view from direction opposite to r vector
        }
        // [rs,gs,bs]ks(-V.R)^p
        return ks.scale(Math.pow(-1d * vr, nShininess));
    }
    private Double3 calcDiffusive(Double3 kd, double nl) {
        return kd.scale(Math.abs(nl));
    }
}
