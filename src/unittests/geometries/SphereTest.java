package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SphereTest class in order to test function of sphere
 */
class SphereTest {
    /**
     * Test getNormal function of Sphere
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests - Only one==============
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
        double sqrt3 = Math.sqrt(1d / 3);
        //TC01: Simple EP test
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), sphere.getNormal(new Point(1, 1, 1)), "Bad normal to sphere");
    }

}