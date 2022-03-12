package primitives;

import java.util.Objects;

/**
 * this class represents a point in three dimension space
 */
public class Point {
    protected Double3 xyz;
    /**
     * Constructor to initialize Point with three number values
     *
     * @param x first number coordinate
     * @param y second number coordinate
     * @param z third number coordinate
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * Constructor to initialize Point with Double3
     * @param xyz Double3
     */
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Vector Point(Point p2) {
        return new Vector(xyz.subtract(p2.xyz));
    }

    /**
     * Adds a vector to point where each couple of numbers is added
     * @param vec vector which is added to point
     * @return Point after addition
     */
    public Point add(Vector vec) {
        return new Point(xyz.add(vec.xyz));
    }

    /**
     * subtracts two points into a vector where each couple of numbers is subtracted
     * @param p2 right handle side operand for subtraction
     * @return vector from subtraction
     */
    public Vector subtract(Point p2) {
        return new Vector(xyz.subtract(p2.xyz));
    }

    /**
     * calculates the distance between two points and returns the distance squared
     * @param p2 right handle side operand for distance calculation
     * @return distance squared in double
     */
    public double distanceSquared(Point p2) {
        Double3 tmp = xyz.subtract(p2.xyz);
        return tmp.d1* tmp.d1+
                tmp.d2*tmp.d2+
                tmp.d3* tmp.d3;
    }

    /**
     * calculates the distance between two points
     * @param p2 right handle side operand for distance calculation
     * @return distance in double
     */
    public double distance(Point p2) {
        return Math.sqrt(distanceSquared(p2));
    }

    public Double3 getXyz() {
        return xyz;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(xyz);
    }
}
