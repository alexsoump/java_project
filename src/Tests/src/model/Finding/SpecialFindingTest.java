package src.model.Finding;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the SpecialFinding class.
 */
public class SpecialFindingTest {

    @Test
    public void testConstructorValidInput() {
        // Test valid construction with DaxtylidiMinwa
        SpecialFinding finding = new SpecialFinding(SpecialFindings.DaxtylidiMinwa);
        assertEquals("Points for DaxtylidiMinwa should be 35", 35, finding.getPoints());
        assertEquals("Type should be DaxtylidiMinwa", SpecialFindings.DaxtylidiMinwa, finding.getType());

        // Test valid construction with another type
        finding = new SpecialFinding(SpecialFindings.DiskosFaistou);
        assertEquals("Points for DiskosFaistou should be 25", 25, finding.getPoints());
        assertEquals("Type should be DiskosFaistou", SpecialFindings.DiskosFaistou, finding.getType());
    }

    @Test
    public void testGetPoints() {
        // Test getPoints for different types
        SpecialFinding finding = new SpecialFinding(SpecialFindings.KosmhmaMaliwn);
        assertEquals("Points for KosmhmaMaliwn should be 25", 25, finding.getPoints());
    }

    @Test
    public void testGetType() {
        // Test getType method
        SpecialFinding finding = new SpecialFinding(SpecialFindings.RytoZakrou);
        assertEquals("Type should be RytoZakrou", SpecialFindings.RytoZakrou, finding.getType());
    }

    @Test
    public void testToString() {
        // Test toString method
        SpecialFinding finding = new SpecialFinding(SpecialFindings.DaxtylidiMinwa);
        String expected = "SpecialFinding<DaxtylidiMinwa> Points<35>";
        assertEquals("toString should match the expected format", expected, finding.toString());
    }
}
