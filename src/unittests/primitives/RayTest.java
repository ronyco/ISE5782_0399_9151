package unittests.primitives;

import primitives.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing Ray class
 */
public class RayTest {

    @Test
    /**
     * test getPoint method
     */
    void testGetPoint()
    {
        Point p=new Point(1,1,1);
        Vector v=new Vector(1,0,0);
        Ray ray = new Ray(p,v);

        // ============ Equivalence Partitions Tests ==============
        // EP01: Check when scaling with number
        assertEquals(new Point(4,1,1),ray.getPoint(3),"wrong point");
        // EP02: Check when scaling with negative number
        assertEquals(new Point(-2,1,1),ray.getPoint(-3),"wrong point");

        // =============== Boundary Values Tests ==================
        // BV01: Check when scaling with 0
        assertEquals(p,ray.getPoint(0),"wrong point");
    }

    /**
     * test findClosestPoint method
     */
    @Test
    void findClosestPointTest(){
        Ray ray = new Ray(new Point(1,1,1), new Vector(1,1,1));

        // ============ Equivalence Partitions Tests ==============
        // EP01: closest point somewhere in the middle of list
        assertEquals(new Point(2,2,2),
                ray.findClosestPoint(List.of(new Point(3,3,3), new Point(2,2,2), new Point(4,4,4))),
                "wrong point");

        // =============== Boundary Values Tests ==================
        // BV01: empty list
        assertNull(ray.findClosestPoint(List.of()),
                "empty list");

        // BV02: point at beginning of list
        assertEquals(new Point(2,2,2),
                ray.findClosestPoint(List.of(new Point(2,2,2), new Point(3,3,3), new Point(4,4,4))),
                "wrong point");

        // BV03: point at end of list
        assertEquals(new Point(2,2,2),
                ray.findClosestPoint(List.of(new Point(3,3,3), new Point(4,4,4), new Point(2,2,2))),
                "wrong point");

    }
}
