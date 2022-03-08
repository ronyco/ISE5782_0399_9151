package unittests;

import primitives.*;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for primitives.Point class
 */

class PointTest {

    Point p1 = new Point(1, 2, 3);
    /**
     * Test method for add
     */
    @org.junit.jupiter.api.Test
    void add() {
        //test add function- return value
        assertTrue("ERROR: Point + Vector does not work correctly",
                p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0)));
    }
    /**
     * Test method for subtract
     */
    @org.junit.jupiter.api.Test
    void subtract() {
        //test add subtract- return value
        assertTrue("ERROR: Point + Vector does not work correctly",
                new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)));
    }

    @org.junit.jupiter.api.Test
    void distanceSquared() {
    }

    @org.junit.jupiter.api.Test
    void distance() {
    }
}