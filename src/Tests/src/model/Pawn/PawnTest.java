package src.model.Pawn;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Pawn class.
 */
public class PawnTest {

    @Test
    public void testConstructorInitialState() {
        Pawn pawn = new Pawn();
        assertTrue("Pawn should be hidden initially", pawn.isHidden());
        assertEquals("Initial position should be -1", -1, pawn.getPosition());
        assertNull("Palace should be null initially", pawn.getPalace());
        assertFalse("Pawn should not be placed initially", pawn.isPlaced());
    }

    @Test
    public void testReveal() {
        Pawn pawn = new Pawn();
        pawn.reveal();
        assertFalse("Pawn should not be hidden after reveal", pawn.isHidden());
    }

    @Test
    public void testMoveByValid() {
        Pawn pawn = new Pawn();
        pawn.setPlaced(true); // Place the pawn on the path
        pawn.moveBy(3);
        assertEquals("Position should be updated correctly", 3, pawn.getPosition());
    }

    @Test
    public void testMoveByExceedsPathLimit() {
        Pawn pawn = new Pawn();
        pawn.setPlaced(true); // Place the pawn on the path
        pawn.moveBy(10);
        assertEquals("Position should not exceed 8", 8, pawn.getPosition());
    }

    @Test
    public void testMoveByNotPlaced() {
        Pawn pawn = new Pawn();
        pawn.moveBy(2);
        assertEquals("Pawn should start at position 0 if moved when not placed", 2, pawn.getPosition());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveByNegativeSpaces() {
        Pawn pawn = new Pawn();
        pawn.moveBy(-1); // This should throw an exception
    }

    @Test
    public void testSetPlaced() {
        Pawn pawn = new Pawn();
        pawn.setPlaced(true);
        assertTrue("Pawn should be placed after calling setPlaced(true)", pawn.isPlaced());
        assertEquals("Position should be set to 0 when placed", 0, pawn.getPosition());
    }
}

