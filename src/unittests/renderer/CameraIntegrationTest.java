package unittests.renderer;
import org.junit.jupiter.api.Test;

import geometries.*;;
import primitives.*;
import primitives.Vector;


import renderer.Camera;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/***
 * Testing camera and ray integration with view plane class
 */
public class CameraIntegrationTest {


    /***
     * Helper function: Count number of intersections and do assert equal with expected
     * @param expected number of intersections
     * @param intersectable
     */
    private void assertCountIntersections(int expected, Intersectable intersectable)
    {
        int nX = 3, nY = 3, counter = 0;
        Camera camera = new Camera(new Point(0,0,0.5), new Vector(0,0,-1), new Vector(0,1,0));
        camera.setVPSize(3,3);
        camera.setVPDistance(1);
        List<Point> collectionsOfPoints = null;

        for(int i = 0; i < nY; ++i)
        {
            for(int j = 0; j < nX; ++j)
            {
                var intersections = intersectable.findIntersections(camera.constructRay(nX,nY,j,i));
                if(intersections != null)
                {
                    if(collectionsOfPoints == null) //First Time allocation
                    {
                        collectionsOfPoints = new LinkedList<>();
                    }
                    collectionsOfPoints.addAll(intersections);
                }
                counter += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(expected, counter, "Wrong numbers of intersections");
    }

    /**
     * Integration tests of camera and a sphere.
     */
    @Test
    public void CameraRaySphereIntegrationTest()
    {
        //TC01: Sphere behind camera 0 points
        assertCountIntersections(0,new Sphere(new Point(0, 0, 1), 0.5));
        //TC02: Camera inside sphere, all rays intersect only once 9 points
        assertCountIntersections(9,new Sphere(new Point(0, 0, -1), 4));
        //TC03: Small sphere 2 points
        assertCountIntersections(2,new Sphere(new Point(0, 0, -2.5), 1));
        //TC04: Medium sphere 10 points
        assertCountIntersections(10,new Sphere(new Point(0, 0, -2), 2));
        //TC05: Big sphere 18 points
        assertCountIntersections(18,new Sphere(new Point(0, 0, -2.5), 2.5));
    }

    /**
     * Integration tests of camera and plane.
     */
    @Test
    public void cameraRayPlaneIntegrationTest() {
        //TC01: Plane parallel to view plane 9 points
        assertCountIntersections(9,new Plane(new Point(0, 0, -2), new Vector(0, 0, 1)));
        //TC02: Plane is in front of view plane and cross
        assertCountIntersections(9,new Plane(new Point(0, 0, -1.5), new Vector(0, -0.5, 1)));
        //TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(6,new Plane(new Point(0, 0, -3), new Vector(0, -1, 1)));
    }
    /**
     * Integration tests of camera and triangle.
     */
    @Test
    public void cameraRayTriangleIntegration() {
        // TC01: Small triangle 1 point
        assertCountIntersections(1, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)));
        // TC02: Medium triangle 2 points
        assertCountIntersections(2, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)));
    }
}
