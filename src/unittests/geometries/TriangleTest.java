package unittests.geometries;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @Test
    public void findIntersections() {
        Triangle tr = new Triangle(
                new Point(2, 0, 0),
                new Point(0,2, 0),
                new Point(0, -2, 0));
        Plane pl = new Plane(
                new Point(2, 0, 0),
                new Point(0, 2, 0),
                new Point(0, -2, 0));
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside Triangle
        ray = new Ray(new Point(1, 0, -1), new Vector(0, 0, 1));
        assertEquals(List.of(
                        new Point(1, 0, 0)),
                tr.findIntersections(ray),
                "Bad intersection");

        // TC02: Against edge of Triangle
        ray = new Ray(new Point(3, 0, -1), new Vector(0, 0, 1));
        assertEquals(List.of(new Point(3, 0, 0)), pl.findIntersections(ray),
                "Error: Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Error: Bad intersection");

        // TC03: Against vertex of Triangle
        ray = new Ray(new Point(-1, 0, -1), new Vector(0, 0, 1));
        assertEquals(List.of(new Point(-1, 0, 0)), pl.findIntersections(ray),
                "Error: Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Bad intersection");

        // =============== Boundary Values Tests ==================
        // TC11: In vertex
        ray = new Ray(new Point(0, 1, -1), new Vector(0, 0, 1));
        assertEquals(List.of(new Point(0, 1, 0)), pl.findIntersections(ray),
                "Error: Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Bad intersection");

        // TC12: On edge
        ray = new Ray(new Point(2, 0, -1), new Vector(0, 0, 1));
        assertEquals(List.of(new Point(2, 0, 0)), pl.findIntersections(ray),
                "Error: Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Bad intersection");

        // TC13: On edge continuation
        ray = new Ray(new Point(0, 3, -1), new Vector(0, 0, 1));
        assertEquals(List.of(new Point(0, 3, 0)), pl.findIntersections(ray),
                "Error: Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Bad intersection");

    }
}

