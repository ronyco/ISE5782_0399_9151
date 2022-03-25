package renderer;

import primitives.*;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera viewing objects through a view plane
 */
public class Camera {
    private Point p0;
    private Vector vTo,vUp,vRight;
    private double height, width, distance;

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
        this.width=width;
        this.height=height;
        return this;
    }

    /**
     * setter for distance of view plane
     * @param distance of plane
     * @return camera
     */
    public Camera setVPDistance(double distance){
        this.distance=distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
        //Center of image
        Point pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width and weight)
        double rY = (double) height / nY;
        double rX = (double) width / nX;

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
        return height;
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
        return width;
    }

    /**
     * Distance getter
     * @return Distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Point getter
     * @return Point
     */
    public Point getPoint() {
        return p0;
    }
}
