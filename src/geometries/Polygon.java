package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;
    private int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
        size = vertices.length;
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }


    @Override
    public Box setBoundBox() {
        double minX = vertices.get(0).getX();
        double maxX = minX;
        double minY = vertices.get(0).getY();
        double maxY = minY;
        double minZ = vertices.get(0).getZ();
        double maxZ = minZ;
        double x = 0, y = 0, z = 0;
        for (Point p : vertices) {
            x = p.getX();
            y = p.getY();
            z = p.getZ();
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
            if (z < minZ) minZ = z;
            if (z > maxZ) maxZ = z;
        }
        return new Box(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = plane.findGeoIntersections(ray);

        //If there is no intersections with plane return null
        if (result == null) {
            return null;
        }

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Point P1 = vertices.get(1);
        Point P2 = vertices.get(0);

        Vector v1 = P1.subtract(P0);
        Vector v2 = P2.subtract(P0);

        double sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

        if (isZero(sign)) {
            return null;
        }

        boolean positive = sign > 0;

        //Iteration - for loop, through all vertices of polygon
        for (int i = vertices.size() - 1; i > 0; --i) {
            v1 = v2;
            v2 = vertices.get(i).subtract(P0);

            sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
            if (isZero(sign)) {
                return null;
            }

            if (positive != (sign > 0)) {
                return null;
            }
        }

        return List.of(new GeoPoint(this, result.get(0).point));

    }


}