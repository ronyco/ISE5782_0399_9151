package unittests.primitives;

import primitives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * PointTest class in order to test function of point
 */
class PointTest {

    Point p1 = new Point(1, 2, 3);

    /**
     * Test method for add
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point addition calculation error
        Point p = new Point(1,2,3);
        assertEquals(p.add(new Vector(-1,-2,-3)),new Point(0,0,0),"ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for subtract
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point subtraction wrong calculation
        Point p = new Point(1,2,3);
        assertEquals(new Vector(1,1,1), new Point(2,3,4).subtract(p),"ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for distance squared function
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance squared wrong calculation
        Point p = new Point(1,2,3);
        Point p2 = new Point(4,5,6);
        assertEquals(27, p.distanceSquared(p2), "ERROR: wrong squared distance between points");
    }

    /**
     * Test method for distance function
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance wrong calculation
        Point p = new Point(1,1,1);
        Point p2 = new Point(-1,1,1);
        assertEquals(2, p.distance(p2), "ERROR: wrong distance between points");
    }
}