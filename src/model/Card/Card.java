package src.model.Card;

import src.model.Board.Board;
import src.model.Player.Player;
import src.model.Round.Round;

/**
 * Represents a general card in the game.
 * Each card is associated with a specific palace and has behavior to check playability.
 */
public abstract class Card {
    /**
     * The palace to which this card belongs.
     */
    protected final Palaces palace;

    /**
     * Constructs a card and associates it with a specific palace.
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
    public Card(Palaces palace) {
        this.palace = palace;
    }
    /**
     * Retrieves the palace associated with this card.
     * <p>
     * Preconditions:
     * - The card must have been properly initialized.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the palace this card is associated with.
     * </p>
     *
     * @return the palace associated with this card
     */
    public Palaces getPalace() {

        return palace;
    }

    /**
     * applies the effect of the card in the game
     * @param board the game board
     * @param currentPlayer the current player
     * @param currentRound the current round
     */
    public void applyEffect(Board board, Player currentPlayer, Round currentRound) {
        // Step 1: Null checks for inputs
        if (board == null || currentPlayer == null || currentRound == null) {
            System.err.println("Error: One or more parameters are null in applyEffect.");
            return; // Exit early
        }

        // Step 2: Handle NumberCard
        if (this instanceof NumberCard) { // Check if it's a NumberCard
            NumberCard numberCard = (NumberCard) this; // cast
            NumberCard lastCard = currentPlayer.getLastCard(this.getPalace());

            // Compare values and move the pawn
            if (lastCard.getValue() <= numberCard.getValue()) {
                try {
                    currentPlayer.movePawn(this); // Attempt to move the pawn
                } catch (Exception e) {
                    System.err.println("Error while moving pawn: " + e.getMessage());
                    e.printStackTrace(); // Print error details
                }
            } else {
                System.err.println("Card value is too low to move the pawn.");
            }

        }else if(this instanceof Ariadne){
            if(currentPlayer.getPawnForPath(this.getPalace().ordinal()) != null){
                currentPlayer.movePawn(this);
            }
        }
    }


    /**
     * this makes a string same as the path to the corresponding image
     * @return the string
     */
    @Override
    public String toString(){
        String s = palace.toString().toLowerCase(); // name of the palace in lowercase
        if(this instanceof NumberCard) s+= ((NumberCard) this).getValue(); // add value of number card
        if(this instanceof Ariadne) s+= "Ari";
        if(this instanceof Minotaur) s+= "Min";

        return s; // return the card name as in the image name in the project assets
    }

}
