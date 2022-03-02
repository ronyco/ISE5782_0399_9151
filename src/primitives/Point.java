package primitives;

import java.util.Objects;

public class Point {
    protected Double3 xyz;
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Vector Point(Point p2) {
        return new Vector(xyz.subtract(p2.xyz));
    }
    public Point add(Vector vec) {
        return new Point(xyz.add(vec.xyz));
    }
    public Vector subtract(Point p2) {
        return new Vector(xyz.subtract(p2.xyz));
    }
    public double distanceSquared(Point p2) {
        Double3 tmp = xyz.subtract(p2.xyz);
        return tmp.d1* tmp.d1+
                tmp.d2*tmp.d2+
                tmp.d3* tmp.d3;
    }
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
