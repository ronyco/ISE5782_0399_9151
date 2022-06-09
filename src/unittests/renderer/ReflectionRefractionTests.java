/**
 *
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(0.5)));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of some shapes lighted by some lights
     */
    @Test
    public void ourPicture() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(600, 600).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(new Color(java.awt.Color.magenta)), //
                new Triangle(new Point(-130, -130, -130), new Point(140, -140, -135), new Point(65, 65, -140)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setEmission(new Color(java.awt.Color.cyan)), //
                new Triangle(new Point(150, -150, -150), new Point(-150, 1500, -150),
                        new Point(67, 67, 300)) //
                        .setEmission(new Color(java.awt.Color.ORANGE)) //
                        .setMaterial(new Material().setKr(1.0).setKt(0.5)),
                new Triangle(new Point(150, -150, -150), new Point(-1500, 1500, -1500),
                        new Point(-150, -150, -200)) //
                        .setEmission(new Color(0, 120, 220)) //
                        .setMaterial(new Material().setKr(1).setKt(0.5)),
                new Sphere(new Point(140, -150, -100), 25) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere(new Point(140, -60, -100), 15) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere(new Point(140, 10, -100), 25) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere(new Point(10000, 80, -100), 15) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere(new Point(-100, 150, 50), 35) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKr(1)),
                new Sphere(new Point(50, 100, 50), 5) //
                        .setEmission(new Color(java.awt.Color.magenta)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere(new Point(70, -10, -100), 10) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere(new Point(75, 75, 50), 30) //
                        .setEmission(new Color(java.awt.Color.yellow)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKt(0.6)),
                new Sphere(new Point(-350, -300, -400), 400) //
                        .setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
                new Sphere(new Point(-350, -300, -400), 200) //
                        .setEmission(new Color(100, 120, 120)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(100, 100, 0), new Point(-70, 70, -140), new Point(140, -140, -125)).setEmission(new Color(java.awt.Color.blue)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))

        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKq(2E-7)); //.setkQ(0.000005));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)));


        ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void shadowTrianglesSphereDiffuse() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKt(0.5).setkB(0.001)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60).setKt(0.5).setkB(0.007)), //

                new Sphere(new Point(-50, -50, -1000), 50d) //
                        .setEmission(new Color(java.awt.Color.red)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        ); //
        scene.lights.add( //
                new DirectionalLight(new Color(blue), new Vector(-1, -1, -4)) //
        );

        camera.setImageWriter(new ImageWriter("shadowTrianglesSphereDifuse", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void twoSpheresOnMirrorsGLOSS() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(0.5).setkG(0.02)));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirroredGLOSSS", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * test case for glossy and diffuse surfaces
     */
    @Test
    public void Nice1() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        scene.geometries.add(
                new Sphere(new Point(-3.25, 8, 3), 3).setEmission(new Color(red))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setkG(0.005)),
                new Sphere(new Point(3.25, 8, 3), 3).setEmission(new Color(10, 100, 100)).setMaterial(
                        (new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)),
                new Sphere(new Point(0, 0, 1.5), 1.5).setEmission(new Color(orange))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                new Sphere(new Point(3, 1, 1.5), 1.5).setEmission(new Color(yellow))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                new Sphere(new Point(-3, 1, 1.5), 1.5).setEmission(new Color(green))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                new Sphere(new Point(6, 2, 1.5), 1.5).setEmission(new Color(blue))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                new Sphere(new Point(-6, 2, 1.5), 1.5).setEmission(new Color(magenta))
                        .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                new Polygon(new Point(-7.5, -10, 1), new Point(-7.5, -0.5, 2), new Point(-4.5, -0.5, 2), new Point(-4.5, -10, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5).setkB(0.001)),
                new Polygon(new Point(-4.6, -10, 1), new Point(-4.6, -0.5, 1.5), new Point(-1.6, -0.5, 1.5), new Point(-1.6, -10, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5).setkB(0.002)),
                new Polygon(new Point(-1.5, -10, 1), new Point(-1.5, -0.5, 1.5), new Point(1.4, -0.5, 1.5), new Point(1.4, -10, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5).setkB(0.003)),
                new Polygon(new Point(1.5, -10, 1), new Point(1.5, -0.5, 1.5), new Point(4.4, -0.5, 1.5), new Point(4.4, -10, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5).setkB(0.004)),
                new Polygon(new Point(4.5, -10, 1), new Point(4.5, -0.5, 1.5), new Point(7.5, -0.5, 1.5), new Point(7.5, -10, 1))
                        .setEmission(new Color(0, 0, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5).setkB(0.005)),
                new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple6", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * test case for glossy and diffuse surfaces
     */
    @Test
    public void Nice2() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 100; i++)
            scene.geometries.add(
                    new Sphere(new Point(-3.25, 8, 3), 3).setEmission(new Color(red))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)).createBox(),
                    new Sphere(new Point(3.25, 8, 3), 3).setEmission(new Color(10, 100, 100)).setMaterial(
                            (new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)).createBox(),
                    new Sphere(new Point(0, 0, 1.5), 1.5).setEmission(new Color(orange))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox(),
                    new Sphere(new Point(3, 1, 1.5), 1.5).setEmission(new Color(yellow))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox(),
                    new Sphere(new Point(-3, 1, 1.5), 1.5).setEmission(new Color(green))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox(),
                    new Sphere(new Point(6, 2, 1.5), 1.5).setEmission(new Color(blue))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox(),
                    new Sphere(new Point(-6, 2, 1.5), 1.5).setEmission(new Color(magenta))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox(),
                    new Polygon(new Point(-7.5, -10, 1), new Point(-7.5, -0.5, 2), new Point(-4.5, -0.5, 2), new Point(-4.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox(),
                    new Polygon(new Point(-4.6, -10, 1), new Point(-4.6, -0.5, 1.5), new Point(-1.6, -0.5, 1.5), new Point(-1.6, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox(),
                    new Polygon(new Point(-1.5, -10, 1), new Point(-1.5, -0.5, 1.5), new Point(1.4, -0.5, 1.5), new Point(1.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox(),
                    new Polygon(new Point(1.5, -10, 1), new Point(1.5, -0.5, 1.5), new Point(4.4, -0.5, 1.5), new Point(4.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox(),
                    new Polygon(new Point(4.5, -10, 1), new Point(4.5, -0.5, 1.5), new Point(7.5, -0.5, 1.5), new Point(7.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox(),
                    new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).createBox());
        scene.geometries.createBox();
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple6", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * test case for glossy and diffuse surfaces
     */
    @Test
    public void Nice3() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 100; i++)
            scene.geometries.add(
                    new Sphere(new Point(-3.25, 8, 3), 3).setEmission(new Color(red))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)),
                    new Sphere(new Point(3.25, 8, 3), 3).setEmission(new Color(10, 100, 100)).setMaterial(
                            (new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)),
                    new Sphere(new Point(0, 0, 1.5), 1.5).setEmission(new Color(orange))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                    new Sphere(new Point(3, 1, 1.5), 1.5).setEmission(new Color(yellow))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                    new Sphere(new Point(-3, 1, 1.5), 1.5).setEmission(new Color(green))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                    new Sphere(new Point(6, 2, 1.5), 1.5).setEmission(new Color(blue))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                    new Sphere(new Point(-6, 2, 1.5), 1.5).setEmission(new Color(magenta))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))),
                    new Polygon(new Point(-7.5, -10, 1), new Point(-7.5, -0.5, 2), new Point(-4.5, -0.5, 2), new Point(-4.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)),
                    new Polygon(new Point(-4.6, -10, 1), new Point(-4.6, -0.5, 1.5), new Point(-1.6, -0.5, 1.5), new Point(-1.6, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)),
                    new Polygon(new Point(-1.5, -10, 1), new Point(-1.5, -0.5, 1.5), new Point(1.4, -0.5, 1.5), new Point(1.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)),
                    new Polygon(new Point(1.5, -10, 1), new Point(1.5, -0.5, 1.5), new Point(4.4, -0.5, 1.5), new Point(4.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)),
                    new Polygon(new Point(4.5, -10, 1), new Point(4.5, -0.5, 1.5), new Point(7.5, -0.5, 1.5), new Point(7.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)),
                    new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple6", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Nice4() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        Geometries[] geometries = new Geometries[13];
        for (int i = 0; i < 13; i++) {
            geometries[i] = new Geometries();
        }
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 100; i++) {
            geometries[0].add(
                    new Sphere(new Point(-3.25, 8, 3), 3).setEmission(new Color(red))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)).createBox());
            geometries[1].add(
                    new Sphere(new Point(3.25, 8, 3), 3).setEmission(new Color(10, 100, 100)).setMaterial(
                            (new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)).createBox());
            geometries[2].add(
                    new Sphere(new Point(0, 0, 1.5), 1.5).setEmission(new Color(orange))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox());
            geometries[3].add(
                    new Sphere(new Point(3, 1, 1.5), 1.5).setEmission(new Color(yellow))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox());
            geometries[4].add(
                    new Sphere(new Point(-3, 1, 1.5), 1.5).setEmission(new Color(green))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox());
            geometries[5].add(
                    new Sphere(new Point(6, 2, 1.5), 1.5).setEmission(new Color(blue))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox());
            geometries[6].add(
                    new Sphere(new Point(-6, 2, 1.5), 1.5).setEmission(new Color(magenta))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))).createBox());
            geometries[7].add(
                    new Polygon(new Point(-7.5, -10, 1), new Point(-7.5, -0.5, 2), new Point(-4.5, -0.5, 2), new Point(-4.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox());
            geometries[8].add(
                    new Polygon(new Point(-4.6, -10, 1), new Point(-4.6, -0.5, 1.5), new Point(-1.6, -0.5, 1.5), new Point(-1.6, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox());
            geometries[9].add(
                    new Polygon(new Point(-1.5, -10, 1), new Point(-1.5, -0.5, 1.5), new Point(1.4, -0.5, 1.5), new Point(1.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox());
            geometries[10].add(
                    new Polygon(new Point(1.5, -10, 1), new Point(1.5, -0.5, 1.5), new Point(4.4, -0.5, 1.5), new Point(4.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox());
            geometries[11].add(
                    new Polygon(new Point(4.5, -10, 1), new Point(4.5, -0.5, 1.5), new Point(7.5, -0.5, 1.5), new Point(7.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)).createBox());
            geometries[12].add(
                    new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)).createBox());
        }
        for (int i = 0; i < 13; i++) {
            scene.geometries.add(geometries[i].createBox());
        }
        scene.geometries.createBox();
        scene.geometries.autoBVH(10);
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple6", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Nice5() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        Geometries[] geometries = new Geometries[13];
        for (int i = 0; i < 13; i++) {
            geometries[i] = new Geometries();
        }
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 100; i++) {
            geometries[0].add(
                    new Sphere(new Point(-3.25, 8, 3), 3).setEmission(new Color(red))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)));
            geometries[1].add(
                    new Sphere(new Point(3.25, 8, 3), 3).setEmission(new Color(10, 100, 100)).setMaterial(
                            (new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5)));
            geometries[2].add(
                    new Sphere(new Point(0, 0, 1.5), 1.5).setEmission(new Color(orange))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))));
            geometries[3].add(
                    new Sphere(new Point(3, 1, 1.5), 1.5).setEmission(new Color(yellow))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))));
            geometries[4].add(
                    new Sphere(new Point(-3, 1, 1.5), 1.5).setEmission(new Color(green))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))));
            geometries[5].add(
                    new Sphere(new Point(6, 2, 1.5), 1.5).setEmission(new Color(blue))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))));
            geometries[6].add(
                    new Sphere(new Point(-6, 2, 1.5), 1.5).setEmission(new Color(magenta))
                            .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100))));
            geometries[7].add(
                    new Polygon(new Point(-7.5, -10, 1), new Point(-7.5, -0.5, 2), new Point(-4.5, -0.5, 2), new Point(-4.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)));
            geometries[8].add(
                    new Polygon(new Point(-4.6, -10, 1), new Point(-4.6, -0.5, 1.5), new Point(-1.6, -0.5, 1.5), new Point(-1.6, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)));
            geometries[9].add(
                    new Polygon(new Point(-1.5, -10, 1), new Point(-1.5, -0.5, 1.5), new Point(1.4, -0.5, 1.5), new Point(1.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)));
            geometries[10].add(
                    new Polygon(new Point(1.5, -10, 1), new Point(1.5, -0.5, 1.5), new Point(4.4, -0.5, 1.5), new Point(4.4, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)));
            geometries[11].add(
                    new Polygon(new Point(4.5, -10, 1), new Point(4.5, -0.5, 1.5), new Point(7.5, -0.5, 1.5), new Point(7.5, -10, 1))
                            .setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.5)));
            geometries[12].add(
                    new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)).setEmission(new Color(10, 100, 100))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        }
        for (int i = 0; i < 13; i++) {
            scene.geometries.add(geometries[i]);
        }
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple7", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Nice6() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 200; i++) {
            scene.geometries.add(new Sphere(new Point(-3.25, 8 * i * 0.01, 3), 0.2).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 200; i++) {
            scene.geometries.add(new Sphere(new Point(-3.25, 8 * i * 0.01, 1), 0.2).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 200; i++) {
            scene.geometries.add(new Sphere(new Point(-3.25, 8 * i * 0.01, -3), 0.2).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }

        scene.geometries.createBox();
        scene.geometries.autoBVH(5);

        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple6", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Nice7() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 120; i++) {
            scene.geometries.add(new Sphere(new Point(-9.25, 8 * i * 0.01, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 140; i++) {
            scene.geometries.add(new Sphere(new Point(-7.25, 8 * i * 0.02, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 160; i++) {
            scene.geometries.add(new Sphere(new Point(-5.25, 8 * i * 0.03, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 180; i++) {
            scene.geometries.add(new Sphere(new Point(-3.25, 8 * i * 0.04, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 200; i++) {
            scene.geometries.add(new Sphere(new Point(-1.25, 8 * i * 0.05, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 220; i++) {
            scene.geometries.add(new Sphere(new Point(1.25, 8 * i * 0.06, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 240; i++) {
            scene.geometries.add(new Sphere(new Point(3.25, 8 * i * 0.07, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 260; i++) {
            scene.geometries.add(new Sphere(new Point(5.25, 8 * i * 0.08, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 280; i++) {
            scene.geometries.add(new Sphere(new Point(7.25, 8 * i * 0.09, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }
        for (int i = 0; i < 300; i++) {
            scene.geometries.add(new Sphere(new Point(9.25, 8 * i * 0.1, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)));
        }

        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple7", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void Nice8() {
        Camera camera = new Camera(new Point(0, -50, 20), new Vector(0, 3, -1), new Vector(0, 1, 3)) //
                .setVPSize(140, 100).setVPDistance(400) //
                .setRayTracer(new RayTracerBasic(scene));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.15)));
        for (int i = 0; i < 120; i++) {
            scene.geometries.add(new Sphere(new Point(-9.25, 8 * i * 0.01, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 140; i++) {
            scene.geometries.add(new Sphere(new Point(-7.25, 8 * i * 0.02, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 160; i++) {
            scene.geometries.add(new Sphere(new Point(-5.25, 8 * i * 0.03, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 180; i++) {
            scene.geometries.add(new Sphere(new Point(-3.25, 8 * i * 0.04, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 200; i++) {
            scene.geometries.add(new Sphere(new Point(-1.25, 8 * i * 0.05, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 220; i++) {
            scene.geometries.add(new Sphere(new Point(1.25, 8 * i * 0.06, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 240; i++) {
            scene.geometries.add(new Sphere(new Point(3.25, 8 * i * 0.07, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 260; i++) {
            scene.geometries.add(new Sphere(new Point(5.25, 8 * i * 0.08, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 280; i++) {
            scene.geometries.add(new Sphere(new Point(7.25, 8 * i * 0.09, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        for (int i = 0; i < 300; i++) {
            scene.geometries.add(new Sphere(new Point(9.25, 8 * i * 0.1, -3), 0.1).setEmission(new Color(red))
                    .setMaterial((new Material().setKd(0.5).setKs(0.5).setShininess(100)).setKr(0.5).setKt(0.5)).createBox());
        }
        scene.geometries.createBox();
        scene.geometries.autoBVH(2);
        scene.lights.add(new DirectionalLight(new Color(200, 200, 200), new Vector(0, 0, -1)));
        camera.setImageWriter(new ImageWriter("Apple8", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

}
