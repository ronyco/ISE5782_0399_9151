package unittests.geometries;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



/**
 * PlaneTest class in order to test function of plane
 */

class PlaneTest {
    /**
     * Test constructor of plane
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //EP01: Plane with two equal points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,3),new Point(1,2,3),
                new Point(3,4,7)), "constructed a plane with points that coincide");

        //EP02: Plane with points on the same ray
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(2,2,2),
                new Point(3,3,3)), "constructed a plane with points on the same line");
    }

    /**
     * Test getNormal of plane
     */
    @Test
    void getNormal() {
        Point p1 = new Point(0,0,0);
        Point p2 = new Point(0,1,0);
        Point p3 = new Point(0,0,1);
        Plane plane = new Plane(p1,p2,p3);

        // ============ Equivalence Partitions Tests - Only One ==============
        //EP01: Single Equivalence Partition test. We don't know in which direction is normal vector
        //Test pass if one of condition pass
        assertTrue(plane.getNormal().equals(new Vector(-1,0,0)) ||
                plane.getNormal().equals(new Vector(-1,0,0).scale(-1)));
    }

    @Test
    void findIntersections() {

        Plane plane = new Plane(new Point(2,0,0),new Point(0,2,0),new Point(-2,0,0));

        // ============ Equivalence Partitions Tests ==============

        // EP01: Ray intersects the plane(1 point)
        Point p1 = new Point(2, 0, 0);
        List<Point> result = plane.findIntersections(new Ray(new Point(1, 0, -1),
                new Vector(1, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray intersects the plane");

        // EP02: Ray does not intersect the plane(0 points)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 1), new Vector(1, 0, 1))),
                "Ray does not intersect plane");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane(0 points)

        // BV03: Ray is included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))),
                "Ray is included in the plane");

        // BV04: Ray is not included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 1), new Vector(1, 0, 0))),
                "Ray is not included in the plane");

        // **** Group: Ray is orthogonal to the plane

        // BV05: Ray starts before the plane(1 point)
        p1 = new Point(1, -1, 0);
        result = plane.findIntersections(new Ray(new Point(1, -1, -1),
                new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0), "Ray starts before the plane");

        // BV06: Ray starts in plane(0 point)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1))),
                "Ray starts in plane");

        // BV07: Ray starts after plane(0 point)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 1), new Vector(0, 0, 1))),
                "Ray starts after plane");

        // BV08: Ray is neither orthogonal nor parallel to and begins at the plane(0 point)
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 1, 1))),
                "Ray is neither orthogonal nor parallel to and begins at the plane");

        // BV09: Ray is neither orthogonal nor parallel to the plane and begins in
        // the same point which appears as reference point in the plane(0 point)
        assertNull(plane.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 1))),
                "Ray begins at reference point of the plane");
    }

}