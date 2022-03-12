package unittests;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * test get normal function
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests - Only one==============
        Sphere sphere = new Sphere(new Point(0,0,0),1);
        double sqrt3 = Math.sqrt(1d / 3);
        //test normal for sphere
        assertEquals(new Vector(sqrt3,sqrt3,sqrt3), sphere.getNormal(new Point(1,1,1)),"Bad normal to sphere");
    }

}