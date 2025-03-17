package src.model.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.model.Card.Palaces;
import src.model.Card.*;
import src.model.Pawn.Pawn;
import src.model.Finding.*;
import java.util.ArrayList;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Test Player");
    }

    @Test
    void testGetName() {
        assertEquals("Test Player", player.getName(), "The player's name should be 'Test Player'.");
    }

    @Test
    void testAddScore() {
        player.addScore(10);
        assertEquals(10, player.getScore(), "The player's score should be 10 after adding 10.");
        player.addScore(5);
        assertEquals(15, player.getScore(), "The player's score should be 15 after adding 5.");
    }

    @Test
    void testAddCardToHand() {
        Card card = new NumberCard(5, Palaces.Knossos);
        player.addCardToHand(card);
        assertEquals(card, player.chooseCard(0), "The card should be added to the player's hand.");
    }

    @Test
    void testChooseCard() {
        Card card = new NumberCard(5, Palaces.Knossos);
        player.addCardToHand(card);
        assertEquals(card, player.chooseCard(0), "The chosen card should be the one added.");
    }

    @Test
    void testChooseCardOutOfRange() {
        assertThrows(IndexOutOfBoundsException.class, () -> player.chooseCard(8), "Choosing a card outside of range should throw an exception.");
    }

    @Test
    void testSetLastCardPlayed() {
        NumberCard card = new NumberCard(5, Palaces.Knossos);
        player.setLastCardPlayed(Palaces.Knossos, card);
        assertEquals(card, player.getLastCard(Palaces.Knossos), "The last card played should be the same card set.");
    }

    @Test
    void testRejectCard() {
        Card card = new NumberCard(5, Palaces.Knossos);
        player.addCardToHand(card);
        player.rejectCard(0);
        assertNull(player.chooseCard(0), "The card should be removed from the player's hand.");
    }

    @Test
    void testMovePawn() {
        Card card = new NumberCard(3, Palaces.Knossos);
        player.movePawn(card);
        Pawn pawn = player.getPawnForPath(Palaces.Knossos.ordinal());
        assertNotNull(pawn, "There should be a pawn placed on the path.");
        assertEquals(3, pawn.getPosition(), "The pawn should move by the value of the card.");
    }

    @Test
    void testAddFinding() {
        Mural finding = new Mural(Murals.HappyMan);
        player.addFinding(finding);
        ArrayList<Mural> frescoes = player.getFrescoes();
        assertTrue(frescoes.contains(finding), "The finding should be added to the player's frescoes.");
    }

    @Test
    void testHasCard() {
        Card card = new NumberCard(5, Palaces.Knossos);
        player.addCardToHand(card);
        assertEquals(0, player.hasCard(card), "The card should be found in the player's hand.");
    }

    @Test
    void testGetSnakeGoddesses() {
        SnakeGoddess snakeGoddess = new SnakeGoddess();
        player.addFinding(snakeGoddess);
        ArrayList<SnakeGoddess> snakeGoddesses = player.getSnakeGoddesses();
        assertTrue(snakeGoddesses.contains(snakeGoddess), "The snake goddess should be found in the player's findings.");
    }


    @Test
    void testCalculateScore() {
        player.addFinding(new Mural(Murals.WomanPortrait));
        player.addFinding(new SnakeGoddess());
        player.addFinding(new SpecialFinding(SpecialFindings.RytoZakrou));
        player.calculateScore();
        int expectedScore = 15 + 25;
        assertEquals(expectedScore, player.getScore(), "The score should reflect the findings and pawns' scores.");
    }


    @Test
    void testGetSpecialFindings() {
        Finding finding = new SpecialFinding(SpecialFindings.KosmhmaMaliwn);
        player.addFinding(finding);
        ArrayList<SpecialFinding> specialFindings = player.getSpecialFindings();
        assertTrue(specialFindings.contains(finding), "The special finding should be included in the player's special findings.");
    }

    @Test
    void testGetPawnForPath() {
        Card card = new NumberCard(3, Palaces.Knossos);
        player.movePawn(card);
        Pawn pawn = player.getPawnForPath(Palaces.Knossos.ordinal());
        assertNotNull(pawn, "The player should have a pawn placed on the path.");
    }
}
