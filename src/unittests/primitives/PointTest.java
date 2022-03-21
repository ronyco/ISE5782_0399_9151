package unittests.primitives;

import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * PointTest class in order to test function of point
 */
class PointTest {

    Point p1 = new Point(1, 2, 3);
    Point p2 = new Point(2,3,4);

    /**
     * Test method for add
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point addition calculation error
        assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)), "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for subtract
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // EP01: Point subtraction wrong calculation
        assertEquals(new Vector(1, 1, 1), p2.subtract(p1), "ERROR: Point + Vector does not work correctly");
        // =============== Boundary Values Tests ==================
        // BV01: Subtract of same two points
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "Error: Zero vector is not allowed");
    }

    /**
     * Test method for distance squared function
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance squared wrong calculation
        Point p2 = new Point(4, 5, 6);
        assertEquals(27.0, p1.distanceSquared(p2), 0.0000001, "ERROR: wrong squared distance between points");
        // =============== Boundary Values Tests ==================
        // BV01: Distance with itself
        assertEquals(0.0, p1.distanceSquared(p1), 0.0000001, "ERROR: wrong squared distance between the point and itself");
    }

    /**
     * Test method for distance function
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point distance wrong calculation
        Point p = new Point(1, 1, 1);
        Point p2 = new Point(-1, 1, 1);
        assertEquals(2, p.distance(p2), "ERROR: wrong distance between points");
    }
}