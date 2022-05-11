package renderer;

import geometries.Geometry;
import lighting.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Ray.DELTA;
import static primitives.Util.*;

/**
 * Ray Tracer Basic Class
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Max level recursion for global effects
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * min level attenuation factor in recursion
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K = Double3.ONE;


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
        return calcColor(findClosestIntersection(ray), ray);
    }

    /**
     * Calculate color of point using Phong reflectance model
     *
     * @param gp  Points and its geometry
     * @param ray direction of ray
     * @return color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        if (intersection == null)
            return scene.background;
        Color color = calcLocalEffects(intersection, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculates effects on color
     *
     * @param gp  point and its geometry
     * @param ray Ray
     * @return color of local effect
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Geometry geometry = gp.geometry;
        Point point = gp.point;

        Color color = geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geometry.getNormal(point);

        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color.BLACK;
        Material material = geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(gp, lightSource, l, n, nv)) {
                    Color iL = lightSource.getIntensity(point);
                    color = color.add(iL.scale(calcDiffusive(material.kD, nl)
                            .add(calcSpecular(material.kS, l, n, nl, v, material.nShininess))));
                }
            }
        }
        return color;
    }

    /**
     * calculates Specular effect
     *
     * @param ks         factor of specular
     * @param l          normalized from light source
     * @param n          normal to the intersected geometry surface at the point
     * @param nl         dot product of l by n
     * @param v          direction of the ray
     * @param nShininess factor of shining
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
     * @param nl dot product(n*l)
     * @return diffuse factor
     */
    private Double3 calcDiffusive(Double3 kd, double nl) {
        return kd.scale(Math.abs(nl));
    }

    /**
     * check if there is object shading light source from a point
     *
     * @param gp point with its geometry
     * @param l  direction from light to point
     * @param n  normal to point at the geometry
     * @return true if light is not shaded however return false
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nv < 0 ? DELTA : -1 * DELTA);

        Point point = gp.point.add(epsVector);

        Ray lightRay = new Ray(point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections((lightRay));
        GeoPoint closestPoint = findClosestIntersection(lightRay);
        if (intersections != null)
            if (light.getDistance(gp.point) > closestPoint.point.distance(gp.point)
                    && (closestPoint.geometry.getMaterial().kT).equals(Double3.ZERO))
                return false;

        return intersections == null || !(closestPoint.geometry.getMaterial().kT).equals(Double3.ZERO);

        /***Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
         Point point = gp.point.add(delta);
         Ray lightRay = new Ray(point, lightDirection);
         List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.point));
         return intersections == null;**/
    }

    /**
     * Construct a transparency ray from point. Ray will be moved across normal line in direction of ray
     *
     * @param n        normal vector of the point on geometry
     * @param pointGeo on the geometry
     * @param ray      from the geometry
     * @return new refracted ray
     */
    private Ray constructRefractedRay(Point pointGeo, Ray ray, Vector n) {
        return new Ray(pointGeo, ray.getDir(), n);
    }

    /***
     * Construct reflected ray from given point. Ray will be moved across normal line in direction of ray
     * @param pointGeo represent geo point
     * @param ray direction
     * @param n normal
     * @return new reflected ray
     */
    private Ray constructReflectedRay(Point pointGeo, Ray ray, Vector n) {
        // r = v -2.(v.n).n
        Vector v = ray.getDir();
        double dvn = v.dotProduct(n);

        if (dvn == 0) {
            return null;
        }
        Vector vn = n.scale(-2 * dvn);
        Vector r = v.add(vn);
        // use the constructor with 3 arguments to move the head
        return new Ray(pointGeo, r, n);
    }


    private Color calcGlobalEffects(GeoPoint gp, Ray v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);

        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }

    /**
     * Find the closest point of intersection ray with geometry
     *
     * @param ray intersecting the scene
     * @return closest intersection with geometry
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return (intersections == null || intersections.size() == 0) ? null : ray.findClosestGeoPoint(intersections);
    }


}
