package primitives;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error. Vector zero is not allowed");
    }

    protected Vector(Double3 xyz) {
        super(xyz);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Error. Vector zero is not allowed");
    }
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }
    public double dotProduct(Vector v) {
        Double3 tmp = xyz.product(v.xyz);
        return tmp.d1 + tmp.d2 + tmp.d3;
    }
    public Vector crossProduct(Vector v) {
        double x1 = xyz.d2*v.xyz.d3 - xyz.d3* v.xyz.d2;
        double x2 = xyz.d3*v.xyz.d1 - xyz.d1* v.xyz.d3;
        double x3 = xyz.d1*v.xyz.d2 - xyz.d2* v.xyz.d1;

        return new Vector(x1,x2,x3);
    }
    public double lengthSquared() {
        Double3 tmp = xyz.product(this.xyz);
        return tmp.d1 + tmp.d2 + tmp.d3;
    }
    public double length() {
        return Math.sqrt(lengthSquared());
    }
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
