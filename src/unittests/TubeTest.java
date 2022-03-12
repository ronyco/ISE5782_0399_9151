package unittests;

import geometries.Triangle;
import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    @Test
    void getNormal() {
        Tube t = new Tube(new Ray(new Point(0,0,0),new Vector(1,1,1)),1);
        // ============ Equivalence Partitions Tests-Only One==============
        System.out.println(t.getNormal(new Point(2,2,1)).getXyz().toString());
        assertEquals(new Vector(0,0,1), t.getNormal(new Point(2,2,1)), "Bad normal to Tube");
        // =============== Boundary Values Tests-Only one==================
        assertEquals(new Vector(0,0,1), t.getNormal(new Point(0,0,1)), "Bad normal to Tube");
    }

}