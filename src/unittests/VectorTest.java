package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;


/**
 * Unit tests for primitives.Vector class
 */

class VectorTest {
    /**
     * Test method for Vector constructor.
     */
    @Test
    public void testConstructor() {
    // test zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "constructed a zero vector");

    }


    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void scale() {
    }

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    /**
     * Test method for dotProduct
     */
    @org.junit.jupiter.api.Test
    void dotProduct() {
        //test product of orthogonal vectors
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
        //test dotProduct function
        assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
    }

    /**
     * Test method for crossProduct
     */
    @org.junit.jupiter.api.Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }


    /**
     * Test method for lengthSquared
     */
    @org.junit.jupiter.api.Test
    void lengthSquared() {
        assertTrue("ERROR: lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
    }

    /**
     * Test method for length
     */
    // test lengthSquared
    @org.junit.jupiter.api.Test
    void length() {
        assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
    }

    // test vector normalization vs vector length and cross-product
    Vector v = new Vector(1, 2, 3);
    Vector u = v.normalize();
    /**
     * Test method for normalize
     */
    @org.junit.jupiter.api.Test
    void normalize() {
        //test normalised vector function- length=1
        assertTrue("ERROR: the normalized vector is not a unit vector", !isZero(u.length() -1));
        //test normalised vector function- direction
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
        //test normalised vector function- absolute value
        assertTrue("ERROR: the normalized vector is opposite to the original one", v.dotProduct(u) < 0);
    }
}