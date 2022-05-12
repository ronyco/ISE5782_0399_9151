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
                new Sphere( new Point(140, -150, -100),25) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere( new Point(140, -60, -100),15) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere( new Point(140, 10, -100),25) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere( new Point(10000, 80, -100),15) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere( new Point(-100, 150, 50),35) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKr(1)),
                new Sphere( new Point(50, 100, 50),5) //
                        .setEmission(new Color(java.awt.Color.magenta)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                new Sphere( new Point(70, -10, -100),10) //
                        .setEmission(new Color(java.awt.Color.cyan)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.4)),
                new Sphere( new Point(75, 75, 50),30) //
                        .setEmission(new Color(java.awt.Color.yellow)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKt(0.6)),
                new Sphere( new Point(-350, -300, -400),400) //
                        .setEmission(new Color(0, 0, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
                new Sphere( new Point(-350, -300, -400),200) //
                        .setEmission(new Color(100, 120, 120)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(100,100,0),new Point(-70, 70, -140),new Point(140, -140, -125)).setEmission(new Color(java.awt.Color.blue)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6))

        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0),new Vector(0, 0, -1)) //
                .setKq(2E-7)); //.setkQ(0.000005));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)));



        ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }



}
