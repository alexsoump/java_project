package src.model.Card;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the NumberCard class.
 */
public class NumberCardTest {

    @Test
    public void testConstructorValidInput() {
        // Test valid construction with value 1 and a valid palace
        NumberCard card = new NumberCard(1, Palaces.Knossos);
        assertEquals("Card value should be 1", 1, card.getValue());
        assertEquals("Card palace should be Knossos", Palaces.Knossos, card.getPalace());

        // Test valid construction with value 10 and another palace
        card = new NumberCard(10, Palaces.Malia);
        assertEquals("Card value should be 10", 10, card.getValue());
        assertEquals("Card palace should be Malia", Palaces.Malia, card.getPalace());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidValueTooLow() {
        // Test invalid value less than 0
        new NumberCard(-1, Palaces.Phaistos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidValueTooHigh() {
        // Test invalid value greater than 10
        new NumberCard(11, Palaces.Phaistos);
    }

    @Test
    public void testGetValue() {
        // Test retrieval of card value
        NumberCard card = new NumberCard(7, Palaces.Zakros);
        assertEquals("Card value should be 7", 7, card.getValue());
    }

    @Test
    public void testGetPalace() {
        // Test retrieval of palace
        NumberCard card = new NumberCard(4, Palaces.Phaistos);
        assertEquals("Card palace should be Phaistos", Palaces.Phaistos, card.getPalace());
    }
}
