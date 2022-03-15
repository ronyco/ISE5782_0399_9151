package unittests.geometries;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TriangleTest class in order to test function of triangle
 */
class TriangleTest {
    /***
     * Test getNormal function of Triangle
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1 = new Point(0,0,1);
        Point p2 = new Point(1,0,0);
        Point p3 = new Point(0,1,0);
        Triangle triangle = new Triangle(p1,p2,p3);
        double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components

        Vector v = new Vector(sqrt3,sqrt3,sqrt3);
        Vector vInverse = new Vector(sqrt3,sqrt3,sqrt3).scale(-1);


        assertTrue(v.equals(triangle.getNormal(new Point(0, 0, 1)))
                ||  vInverse.equals(triangle.getNormal(new Point(0, 0, 1))),
                "Bad normal to triangle");
    }
}