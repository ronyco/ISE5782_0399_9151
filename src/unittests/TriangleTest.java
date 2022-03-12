package unittests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests-Only One ==============
        Triangle t = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertTrue("ERROR:Bad normal to plane", t.getNormal(new Point(0, 0, 1)).equals(new Vector(sqrt3, sqrt3, sqrt3))||
                t.getNormal(new Point(0, 0, 1)).equals(new Vector(sqrt3*(-1), sqrt3*(-1), sqrt3*(-1))));
    }

}