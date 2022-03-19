package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;



/**
 * TubeTest class in order to test function of tube
 */
class TubeTest {
    /**
     * Test getNormal function of Tube
     */
    @Test
    void getNormal() {
        Tube t = new Tube(new Ray(new Point(2,2,2),new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests-Only One==============
        //TC01: Simple Test
        assertEquals(new Vector(0,-1,0), t.getNormal(new Point(2,1,0)), "Bad normal to Tube");

        // =============== Boundary Values Tests-Only one==================
        //TC01: Point is against begin of ray
        assertEquals(new Vector(0,-1,0), t.getNormal(new Point(2,1,2)), "Bad normal to Tube");
    }
    @Test
    void findIntersections(){

    }

}