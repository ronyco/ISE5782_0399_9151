package unittests;

import geometries.Triangle;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    void getNormal() {
        Tube t = new Tube(new Ray(new Point(0,0,0),new Vector(1,1,1)),1);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0,0,1), t.getNormal(new Point(2,2,1)), "Bad normal to Tube");
        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(0,0,1), t.getNormal(new Point(0,0,1)), "Bad normal to Tube");
    }

}