package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SphereTest class in order to test function of sphere
 */
class SphereTest {
    /**
     * Test getNormal function of Sphere
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests - Only one==============
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
        double sqrt3 = Math.sqrt(1d / 3);
        //TC01: Simple EP test
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), sphere.getNormal(new Point(1, 1, 1)), "Bad normal to sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        p1 = new Point(0.5, 0.87, 0);
        result = sphere.findIntersections(new Ray(new Point(0.5, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC5: Ray starts at sphere and goes inside (1 points)
        p1 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0),
                new Vector(-1.8, 1.8, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts at sphere and goes inside");

        // TC6: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center

        // TC7: Ray starts before the sphere (2 points)
        p1 = new Point(1, -1, 0);
        p2 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, -2, 0),
                new Vector(0, 4, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray starts before the sphere");

        // TC8: Ray starts at sphere and goes inside (1 points)
        p1 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, -1, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts at sphere and goes inside");

        // TC9: Ray starts inside (1 points)
        p1 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, 0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts inside");

        // TC10: Ray starts at the center (1 points)
        p1 = new Point(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(0, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts at center");

        // TC11: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(0, 1, 0))),
                "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 1, 0))),
                "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");

    }

}