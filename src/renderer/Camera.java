package renderer;

import primitives.*;

/**
 * Camera class represents a camera viewing objects through a view plane
 */
public class Camera {
    private Point point;
    private Vector to,up,right;
    private double height, width, distance;

    /**
     * constructor for Camera
     * @param point camera's position
     * @param to vector to view plane
     * @param up vector up from camera
     */
    public Camera(Point point,  Vector to, Vector up) {
        if(to.dotProduct(up)!=0)
            throw new IllegalArgumentException("Vectors for camera are not orthogonal");

        this.point = point;
        this.to=to.normalize();
        this.up=up.normalize();
        this.right=this.to.crossProduct(this.up);
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
        return null;
    }

    /**
     * up getter
     * @return up
     */
    public Vector getUp() {
        return up;
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
        return to;
    }

    /**
     * Right getter
     * @return Right
     */
    public Vector getRight() {
        return right;
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
        return point;
    }
}
