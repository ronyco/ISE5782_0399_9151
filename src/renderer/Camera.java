package renderer;

import primitives.*;

public class Camera {
    private Point point;
    private Vector to,up,right;
    private double height, width, distance;

    public Camera(Point point,  Vector to, Vector up) {
        if(to.dotProduct(up)==0)
            throw new IllegalArgumentException("Vectors for camera are not orthogonal");

        this.point = point;
        this.to=to.normalize();
        this.up=up.normalize();
        this.right=this.to.crossProduct(this.up);
    }


    public Vector getUp() {
        return up;
    }

    public double getHeight() {
        return height;
    }

    public Vector getTo() {
        return to;
    }

    public Vector getRight() {
        return right;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public Point getPoint() {
        return point;
    }
}
