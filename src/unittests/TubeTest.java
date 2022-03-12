package unittests;

import geometries.Triangle;
import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    /**
     * test get normal function
     */
    @Test
    void getNormal() {
        Tube t = new Tube(new Ray(new Point(2,2,2),new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests-Only One==============
        assertEquals(new Vector(0,-1,0), t.getNormal(new Point(2,1,0)), "Bad normal to Tube");
        // =============== Boundary Values Tests-Only one==================
        assertEquals(new Vector(0,-1,0), t.getNormal(new Point(2,1,1)), "Bad normal to Tube");
    }

}