package unittests.primitives;

import primitives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;


/**
 * VectorTest class in order to test function of vector
 */

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for Vector constructor.
     * Throw Exception if zero vector
     */
    @Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================
        // TC01: Check if we can build zero vector(0,0,0)
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "constructed a zero vector");
    }

    /**
     * Test method for addition function
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test for adding
        assertEquals(new Vector(4,6,7),new Vector(1,2,3).add(new Vector(3,4,4)),"Error: Wrong vector add value");
    }

    /**
     * Test method for subtract function
     */
    @Test
    void subtract()
    {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test for subtract
        Vector v = new Vector(1,2,4);
        Vector v2 = new Vector(7,5,6);
        assertEquals(v.subtract(v2), new Vector(-6,-3,-2), "ERROR: Wrong vector subtract value" );

    }

    /**
     * Test method for scale function
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple test for multiply vector by scale
        assertEquals(new Vector(2,4,6),new Vector(1,2,3).scale(2),"Error: scale function doesn't work.");
    }

    /**
     * Test method for dotProduct function
     */
    @Test
    void dotProduct() {

        // ============ Equivalence Partitions Tests ==============
        //TC01: Simple dot product between vectors
        assertTrue(isZero(v1.dotProduct(v2) + 28),"ERROR: dotProduct() wrong value");
        // =============== Boundary Values Tests ==================
        //TC01: Orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v3)),"ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * Test method for crossProduct function
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for lengthSquared function
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: lengthSquared wrong calculation
        assertTrue(isZero(v1.lengthSquared()-14),"ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for length function
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: length wrong calculation
        assertTrue(isZero(new Vector(0, 3, 4).length()-5),"ERROR: length() wrong value");
    }

    /**
     * Test method for normalize function
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector u = v1.normalize();
        // TC01:  normalize don't work because it doesn't make it unit vector
        assertTrue(isZero(u.length() - 1),"ERROR: the normalized vector is not a unit vector");

        // =============== Boundary Values Tests ==================
        // TC01:  after a cross product normalize vector is not parallel to the original
        assertThrows(IllegalArgumentException.class,() ->v1.crossProduct(u),"ERROR: the normalized vector is not parallel to the original one");
        //TC02: after dot product normalized vector is opposite to the original one
        assertFalse(v1.dotProduct(u) < 0,"ERROR: the normalized vector is opposite to the original one");
    }
}