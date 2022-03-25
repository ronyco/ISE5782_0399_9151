package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

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

    /***
     * Get Point at specific distance in the ray direction
     * @param t distance for reaching new Point
     * @return new {@link Point} who represent point { P | P = P0 + t.v }
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}
