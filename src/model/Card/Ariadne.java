package src.model.Card;

/**
 * Represents an Ariadne card, a type of special card in the game.
 * Ariadne cards are used to assist the player in progressing through the game.
 */
public class Ariadne extends SpecialCard {
    final int value = 2;

    /**
     * Constructs an Ariadne card associated with a specific palace.
     * <p>
     * Preconditions:
     * - The palace parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The card is initialized with the specified palace.
     * </p>
     *
     * @param palace the palace this card belongs to
     */
    public Ariadne(Palaces palace) {
        super(palace);
    }

    public int getValue() {
        return value;
    }
}

