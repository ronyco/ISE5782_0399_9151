package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera viewing objects through a view plane
 */
public class Camera {
    private Point p0;
    private Vector vTo,vUp,vRight;
    private double heightVp, widthVp, distanceToVp;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracerBase;

    /**
     * constructor for Camera
     * @param p0 camera's position
     * @param vTo vector to view plane
     * @param vUp vector up from camera
     */
    public Camera(Point p0,  Vector vTo, Vector vUp) {
        if(!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Vectors for camera are not orthogonal");

        this.p0 = p0;
        this.vTo=vTo.normalize();
        this.vUp=vUp.normalize();
        this.vRight=this.vTo.crossProduct(this.vUp);
    }

    /**
     * setter for width height of view plane
     * @param width of plane
     * @param height of plane
     * @return camera
     */
    public Camera setVPSize(double width, double height){
        this.widthVp=width;
        this.heightVp=height;
        return this;
    }

    /**
     * setter for distance of view plane
     * @param distance of plane
     * @return camera
     */
    public Camera setVPDistance(double distance){
        this.distanceToVp=distance;
        return this;
    }

    /**
     * Function that constructs ray from camera through view plane to geometries
     * @param nX Resolution of view plane x axis
     * @param nY Resolution of view plane y axis
     * @param j number of columns
     * @param i number of rows
     * @return Ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        //Center of image
        Point pc = p0.add(vTo.scale(distanceToVp));

        //Ratio (pixel width and weight)
        double rY = (double) heightVp / nY;
        double rX = (double) widthVp / nX;

        Point pij = pc;

        double yI = -(i - (nY -1)/2d)*rY;
        double xJ = (j - (nX -1)/2d)*rX;

        if(!isZero(xJ))
        {
            pij = pij.add(vRight.scale(xJ));
        }
        if(!isZero(yI))
        {
            pij = pij.add(vUp.scale(yI));
        }
        return new Ray(p0, pij.subtract(p0));
    }

    /**
     * up getter
     * @return up
     */
    public Vector getUp() {
        return vUp;
    }

    /**
     * Height getter
     * @return Height
     */
    public double getHeight() {
        return heightVp;
    }

    /**
     * to getter
     * @return to
     */
    public Vector getTo() {
        return vTo;
    }

    /**
     * Right getter
     * @return Right
     */
    public Vector getRight() {
        return vRight;
    }

    /**
     * Width getter
     * @return Width
     */
    public double getWidth() {
        return widthVp;
    }

    /**
     * Distance getter
     * @return Distance
     */
    public double getDistance() {
        return distanceToVp;
    }

    /**
     * Point getter
     * @return Point
     */
    public Point getPoint() {
        return p0;
    }

    /**
     * set Image Writer
     * @param imageWriter
     * @return Camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * set Ray tracer
     * @param rayTracerBase
     * @return Camera
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage(){
        if(this.p0==null || this.vRight==null || this.vTo==null || this.vUp==null || this.imageWriter==null ||
                this.rayTracerBase==null || this.widthVp==0 || this.heightVp==0)
            throw new MissingResourceException("cannot have an empty object","Camera","render image");
        throw  new UnsupportedOperationException();
    }
}
