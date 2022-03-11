package unittests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

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
        Plane p = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), p.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }
}