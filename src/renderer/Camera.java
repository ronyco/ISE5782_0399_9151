package renderer;

import primitives.*;
import primitives.Point;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera viewing objects through a view plane
 */
public class Camera {
    private final Point p0;
    private final Vector vTo, vUp, vRight;
    private double heightVp = 0, widthVp = 0, distanceToVp = 0;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    /**
     * constructor for Camera
     *
     * @param p0  camera's position
     * @param vTo vector to view plane
     * @param vUp vector up from camera
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Vectors for camera are not orthogonal");

        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * setter for width height of view plane
     *
     * @param width  of plane
     * @param height of plane
     * @return camera
     */
    public Camera setVPSize(double width, double height) {
        this.widthVp = width;
        this.heightVp = height;
        return this;
    }

    /**
     * setter for distance of view plane
     *
     * @param distance of plane
     * @return camera
     */
    public Camera setVPDistance(double distance) {
        this.distanceToVp = distance;
        return this;
    }

    /**
     * Function that constructs ray from camera through view plane's pixel
     *
     * @param nX Resolution of view plane x-axis
     * @param nY Resolution of view plane y-axis
     * @param j  pixel's column number
     * @param i  pixel's row number
     * @return the ray through pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        //Center of image
        Point pc = p0.add(vTo.scale(distanceToVp));

        //Ratio (pixel width and weight)
        double rY = (double) heightVp / nY;
        double rX = (double) widthVp / nX;

        Point pij = pc;

        double yI = -(i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;

        if (!isZero(xJ)) {
            pij = pij.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pij = pij.add(vUp.scale(yI));
        }
        return new Ray(p0, pij.subtract(p0));
    }

    /**
     * up vector getter
     *
     * @return up vector
     */
    public Vector getUp() {
        return vUp;
    }

    /**
     * Height view plane getter
     *
     * @return Height of the view plane
     */
    public double getHeight() {
        return heightVp;
    }

    /**
     * Getter vector toward from the camera to the view plane
     *
     * @return Vector from camera to view plane
     */
    public Vector getTo() {
        return vTo;
    }

    /**
     * Right getter of the camera
     *
     * @return Right vector instance
     */
    public Vector getRight() {
        return vRight;
    }

    /**
     * Width of the view plane getter
     *
     * @return double that represents width of the view plane
     */
    public double getWidth() {
        return widthVp;
    }

    /**
     * Distance getter from camera to view plane
     *
     * @return double that represents distance from camera to view plane
     */
    public double getDistance() {
        return distanceToVp;
    }

    /**
     * Point getter which represents center point of camera
     * @return Point center of camera itself
     */
    public Point getPoint() {
        return p0;
    }

    /**
     * set Image Writer
     * @param imageWriter of camera
     * @return Camera instance itself
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set Ray tracer
     * @param rayTracerBase
     * @return Camera instance itself
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * Render image function that throws exception if not all arguments are passed
     */
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource", "Camera", ImageWriter.class.getName());
        if (rayTracerBase == null)
            throw new MissingResourceException("Missing Resource","Camera", RayTracerBase.class.getName());
        if (this.p0 == null || this.vTo == null || this.vRight == null || this.vUp == null || this.widthVp == 0 || this.heightVp == 0)
            throw new MissingResourceException("Missing Resource", "Camera", Camera.class.getName()); //TODO don't know why not to check

        //rendering the image
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = constructRay(nX, nY, j, i); //TODO replace with cast ray we have to create
                imageWriter.writePixel(j, i, rayTracerBase.traceRay(ray));
            }
        }
    }

    /**
     * Print Grid of the image
     * @param interval      of the grid's line
     * @param intervalColor color of grid's line
     */
    public void printGrid(int interval, Color intervalColor) {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource", "Camera", imageWriter.getClass().getName());
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, intervalColor);
                }
            }
        }
    }

    /**
     * Write image function that create images using writeImage class
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Missing Resource","Camera", imageWriter.getClass().getName());
        imageWriter.writeToImage();
    }
}
