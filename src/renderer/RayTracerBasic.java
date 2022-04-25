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
//add(geoPoint.geometry.getEmission());
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir (); Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v)); if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(calcDiffusive(material.kD, nl, iL),
                        calcSpecular(material.kS, l, n, nl, v, material.nShininess, iL));
            }
        }
        return color;
    }
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector V, int nShininess, Color ip) {
        double p = nShininess;
        if (isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero for scaling the normal vector");
        }
        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double VR = alignZero(V.dotProduct(R));
        if (VR >= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        // [rs,gs,bs]ks(-V.R)^p
        return ip.scale(ks.scale(Math.pow(-1d * VR, p)));
    }
    private Color calcDiffusive(Double3 kd, double nl, Color ip) {
        return ip.scale(kd.scale(Math.abs(nl)));
    }
}
