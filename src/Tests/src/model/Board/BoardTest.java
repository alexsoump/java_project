package src.model.Board;

import org.junit.Before;
import org.junit.Test;
import src.model.Position.FindingPosition;
import src.model.Position.Position;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testConstructorInitializesPaths() {
        assertNotNull("Paths should not be null after Board construction.", board.Paths);
        assertEquals("Paths should have 4 rows (one for each palace).", 4, board.Paths.length);
        for (Position[] row : board.Paths) {
            assertEquals("Each path should have 9 positions.", 9, row.length);
        }
    }

    @Test
    public void testPathsAreFilledCorrectly() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                Position position = board.Paths[i][j];
                assertNotNull("All positions should be initialized.", position);
                if (j == 1 || j == 3 || j == 5 || j == 7 || j == 8) {
                    assertTrue("Positions at indices 1, 3, 5, 7, and 8 should be FindingPosition.",
                            position instanceof FindingPosition);
                } else {
                    assertFalse("Positions at other indices should not be FindingPosition.",
                            position instanceof FindingPosition);
                }
            }
        }
    }

    @Test
    public void testScoresAreAssignedCorrectly() {
        int[] expectedScores = {-20, -15, -10, 5, 10, 15, 30, 35, 50}; // Expected scores
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals("Position scores should match expected values.",
                        expectedScores[j], board.Paths[i][j].getScore());
            }
        }
    }
}
