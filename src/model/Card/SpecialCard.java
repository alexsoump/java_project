package src.model.Card;

/**
 * Represents a special card in the game.
 * Special cards have no value and are used for special actions such as Minotaur attacks.
 * They do not follow the usual card matching rules.
 */
public class SpecialCard extends Card {
    /**
     * Constructs a SpecialCard associated with a specific palace.
     * <p>
     * Preconditions:
     * - The palace parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The card is initialized and associated with the specified palace.
     * </p>
     *
     * @param palace the palace this card belongs to
     */
    public SpecialCard(Palaces palace) {
        super(palace);
    }




}
