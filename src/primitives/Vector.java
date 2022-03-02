package primitives;

/**
 * this class represents a vector in the three dimension using a point
 */
public class Vector extends Point {

    /**
     * Constructor to initialize Vector with three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error. Vector zero is not allowed");
    }

    /**
     * Constructor to initialize Vector with Double3
     * @param xyz Double3
     */
    protected Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error. Vector zero is not allowed");
    }

    /**
     * Adds two vectors into a new vector
     * @param v right handle side operand for addition
     * @return result of addition
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Scale (multiply) vector by a number into a new vector where each
     * number is multiplied by the number
     * @param d right handle side operand for scaling
     * @return result of scaling
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }

    /**
     * dot Product two vectors into a new vector where each couple of
     * numbers is multiplied
     *
     * @param v right handle side operand for dot product
     * @return result of dot product
     */
    public double dotProduct(Vector v) {
        Double3 tmp = xyz.product(v.xyz);
        return tmp.d1 + tmp.d2 + tmp.d3;
    }

    /**
     * cross product two vectors into a new vector where eacj couple of numbers is multiplied
     * @param v right handle side operand for cross product
     * @return result of cross product
     */
    public Vector crossProduct(Vector v) {
        double x1 = xyz.d2*v.xyz.d3 - xyz.d3* v.xyz.d2;
        double x2 = xyz.d3*v.xyz.d1 - xyz.d1* v.xyz.d3;
        double x3 = xyz.d1*v.xyz.d2 - xyz.d2* v.xyz.d1;

        return new Vector(x1,x2,x3);
    }

    /**
     * calculates the length of a vector and returns length squared
     * @return length of vector squared
     */
    public double lengthSquared() {
        Double3 tmp = xyz.product(this.xyz);
        return tmp.d1 + tmp.d2 + tmp.d3;
    }
    /**
     * calculates the length of a vector
     * @return length of vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * normalizes vector(divides each number by vector length)
     * @return normalized vector
     */
    public Vector normalize() {
        double lengthOfVector = length();
        double x1 = xyz.d1/lengthOfVector;
        double x2 = xyz.d2/lengthOfVector;
        double x3 = xyz.d3/lengthOfVector;
        return new Vector(x1,x2,x3);
    }

    @Override
    public Double3 getXyz() {
        return super.getXyz();
    }
}
