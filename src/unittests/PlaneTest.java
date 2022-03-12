package unittests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.out;
import static org.junit.Assert.*;


class PlaneTest {
    @org.junit.Test
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,2,3),new Point(1,2,3),
        new Point(3,4,7)), "constructed a plane with points that coincide");
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1,1,1),new Point(2,2,2),
                new Point(3,3,3)), "constructed a plane with points on the same line");
    }

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests - Only One ==============
        Plane p = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertTrue("ERROR:Bad normal to plane", p.getNormal(new Point(0, 0, 1)).equals(new Vector(sqrt3, sqrt3, sqrt3))||
                p.getNormal(new Point(0, 0, 1)).equals(new Vector(sqrt3*(-1), sqrt3*(-1), sqrt3*(-1))));
    }
}