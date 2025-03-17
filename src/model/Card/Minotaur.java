package src.model.Card;


/**
 * Represents a Minotaur card, a type of special card in the game.
 * Minotaur cards are used to attack opponents and hinder their progress.
 */
public class Minotaur extends SpecialCard {
    /**
     * Constructs a Minotaur card associated with a specific palace.
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
    public Minotaur(Palaces palace) {
        super(palace);
    }

}
