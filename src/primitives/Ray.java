package primitives;

/**
 * Ray class represents a ray in three dimension space, meaning a point with a one-sided direction
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray with point and normal vector
     * @param p0 for point
     * @param dir for normal vector
     */
    public Ray(Point p0, Vector dir){
        this.p0=p0;
        this.dir=dir.normalize();
    }

    /***
     * Returns the point P0 of ray
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    /***
     * Returns a vector who represent direction of the ray
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }
}
