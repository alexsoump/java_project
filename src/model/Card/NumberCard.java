package src.model.Card;


/**
 * Represents a numbered card in the game.
 * Each numbered card has a value between 1 and 10 and belongs to a specific palace.
 */
public class NumberCard extends Card {
    /**
     * The value of the card (1 to 10).
     */
    private final int value;
    /**
     * Constructs a numbered card with the specified value and associated palace.
     * <p>
     * Preconditions:
     * - The value must be between 1 and 10.
     * - The palace parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The card is initialized with the specified value and palace.
     * - Throws an exception if the value is out of range.
     * </p>
     *
     * @param value  the value of the card (1-10)
     * @param palace the palace this card belongs to
     * @throws IllegalArgumentException if the value is not between 1 and 10
     */
    public NumberCard(int value, Palaces palace) {
        super(palace);
        if (value < 0 || value > 10) { // value is zero only to initialize lastCardPlayed
            throw new IllegalArgumentException("Card value must be between 0 and 10.");
        }
        this.value = value;
    }

    /**
     * Retrieves the value of this card.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the value of this card.
     * </p>
     *
     * @return the value of this card (1-10)
     */
    public int getValue() {
        return this.value;
    }




}

