package unittests.geometries;

import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;
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
        //TC01: Plane with two equal points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,3),new Point(1,2,3),
                new Point(3,4,7)), "constructed a plane with points that coincide");

        //TC02: Plane with points on the same ray
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
        //TC01: Single Equivalence Partition test. We don't know in wich direction is normal vector
        //Test pass if one of condition pass
        assertTrue(plane.getNormal().equals(new Vector(-1,0,0)) ||
                plane.getNormal().equals(new Vector(-1,0,0).scale(-1)));
    }
}