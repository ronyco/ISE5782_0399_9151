package renderer;

import geometries.Geometry;
import lighting.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
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

    /**
     * Initial K for calcColor recursion
     */
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
     * @return final color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return gp == null ? scene.background
                : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursive color calculation. Calculate color of point using Phong reflectance model
     *
     * @param gp    Point and its geometry
     * @param ray   direction
     * @param level remain level of recursion
     * @param k     Factor attenuation accumulated for recursion
     * @return final color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    /**
     * calculates effects on color
     *
     * @param gp  point and its geometry
     * @param ray direction
     * @return color of local effect
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
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
                Double3 ktr = transparency(gp, lightSource, l, n, nv);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    //if (unshaded(gp, lightSource, l, n, nv)) {
                    Color iL = lightSource.getIntensity(point).scale(ktr);
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

    /***
     * Method that checks if there is an object shading light source from point
     * @param gp point and its geometry
     * @param light light source
     * @param l direction from light to point
     * @param n normal
     * @param nv n dot product v
     * @return accumulated transparency factor
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1);
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);

        Ray lightRay = new Ray(point, lightDirection, n);
        var intersections = scene.geometries.findGeoIntersections((lightRay));

        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (var intersection : intersections)
            if (light.getDistance(gp.point) > intersection.point.distance(gp.point)) {
                ktr = ktr.product(intersection.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                    return Double3.ZERO;
                }
            }
        return ktr;
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
        double dvn = alignZero(v.dotProduct(n));
        if (dvn == 0)
            return null;

        Vector vn = n.scale(-2 * dvn);
        Vector r = v.add(vn);
        // use the constructor with 3 arguments to move the head
        return new Ray(pointGeo, r, n);
    }


    /***
     * Calculate global effect: reflection and refraction for a point color
     * @param gp point and its geometry
     * @param v ray direction
     * @param level recursion level
     * @param k attenuation factor of reflection/refraction
     * @return global effects color
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray v, int level, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, k, material.kR, material.kG)
                .add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, k, material.kT, material.kB));
    }

    /**
     * Recursion method for calculate global effect: refraction + reflection
     *
     * @param ray   direction
     * @param level recursion level
     * @param k     parameter of recursion
     * @param kx    factor for transparency or reflection
     * @return global effects color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx, double blur) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        var beam = ray.createBeamOfRays(blur);
        Color color = Color.BLACK;
        for (var r : beam) {
            GeoPoint gp = findClosestIntersection(r);
            color = color.add(gp == null ? scene.background : calcColor(gp, r, level - 1, kkx));
        }
        return color.reduce(beam.size()).scale(kx);
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
