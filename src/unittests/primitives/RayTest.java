package unittests.primitives;

import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * PointTest class in order to test function of point
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
}
