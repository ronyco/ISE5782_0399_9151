package unittests.geometries;

import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    public void findIntersections() {
        Geometries geometries = new Geometries(
                new Sphere(new Point(1, 0, 0), 1),
                new Polygon(
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 0, 0),
                        new Point(0, -1, 0)
                ),
                new Triangle(
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(0, 0, 1)
                )
        );
        List<Point> result;

        // ============ Equivalence Partitions Tests ==============
        //EP01: A few geometries intersects
        result = geometries.findIntersections(new Ray(new Point(0.25, -0.25, -1), new Vector(0, 0, 1)));
        assertEquals(3, result.size(), "A few geometries intersects");

        // =============== Boundary Values Tests ==================
        //BV01: All geometries intersects
        result = geometries.findIntersections(new Ray(new Point(0.25, 0.25, -1), new Vector(0, 0, 1)));
        assertEquals(4, result.size(), "All geometries intersects");

        //BV02: Only 1 geometry intersect
        result = geometries.findIntersections(new Ray(new Point(-0.5, 0, -1), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Only 1 geometry intersect");

        //BV03: No geometries intersects
        assertNull(geometries.findIntersections(new Ray(new Point(-2, 0, -1), new Vector(0, 0, 1))), "No geometries intersects");

        //BV04: Empty list of geometries
        assertNull(new Geometries().findIntersections(new Ray(new Point(1, 2, 3), new Vector(2, 2, 2))), "Empty list of geometries");

    }
}