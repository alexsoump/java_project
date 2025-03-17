package src.model.Round;

import src.model.Board.Deck;
import src.model.Card.*;
import src.model.Board.Board;
import src.model.Pawn.*;
import src.model.Player.Player;

/**
 * Represents a round in the game, where players take turns to play cards, move their pawns,
 * and interact with the game state.
 */
public class Round {
    private Player currentPlayer;  // The player currently taking their turn
    private Player[] players;      // Array to store the two players
    private Board board;           // The game board
    private boolean p1Played;      // Tracks if Player 1 has played
    private boolean p2Played;      // Tracks if Player 2 has played

    /**
     * Constructs a new round for the game.
     *
     * @param players the array of two players for the game
     * @param board   the game board
     */
    public Round(Player[] players, Board board) {
        this.players = players;
        this.board = board;
        p1Played = false;
        p2Played = false;
        startRound(); // Start the round with Player 1
    }

    /**
     * Starts the round, setting Player 1 as the starting player.
     */
    public void startRound() {
        currentPlayer = players[0];  // Player 1 starts
    }

    /**
     * Returns the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Executes the player's turn by either discarding or playing the selected card.
     *
     * @param card      the selected card
     * @param playCard  true if the player wants to play the card, false to discard it
     */
    public void playTurn(Player player, Card card, boolean playCard) {
        // Validate if the player has the selected card
        if (player.hasCard(card) == -1) {
            throw new IllegalArgumentException("Round->playTurn: Player does not have the specified card.");
        }

        // Perform the chosen action
        if (playCard) {
            // Play the card and apply its effects
            card.applyEffect(board, currentPlayer, this);
        }



        // Remove the card from the player's hand (whether played or discarded)
        int pos;
        if((pos = currentPlayer.hasCard(card)) != -1) currentPlayer.rejectCard(pos);



        // Mark the turn as completed
        if (currentPlayer == players[0]) {
            p1Played = true;  // Player 1 played
            changeTurn();     // Switch to Player 2
        } else {
            p2Played = true;  // Player 2 played
        }

        // End the round if both players have completed their turns
        if (isRoundOver()) {
            endRound();
        }
    }


    /**
     * Changes the turn to the next player.
     */
    public void changeTurn() {
        if (currentPlayer == players[0]) {
            currentPlayer = players[1]; // Switch to Player 2
            System.out.println("Player 2's Turn.");
        } else {
            System.out.println("New round. Player 1's Turn."); // these are mainly for debugging
            currentPlayer = players[0]; // Go back to Player 1 (new round)
        }
    }


    /**
     * Attacks the opponent's pawn using a Minotaur card.
     * Moves the opponent's pawn back or paralyzes Theseus if targeted.
     *
     * @param mino The Minotaur card used for the attack.
     */
    public void attackOpponent(Minotaur mino) {
        // Identify the opponent
        Player opponent = (currentPlayer == players[0]) ? players[1] : players[0];

        // Find the opponent's pawn in the same path
        int palaceIndex = mino.getPalace().ordinal(); // Get the palace index
        Pawn opponentPawn = opponent.getPawnForPath(palaceIndex);

        if (opponentPawn != null && opponentPawn.getPosition() > 0) {
            // If opponent has Theseus, paralyze them
            if (opponentPawn instanceof Theseus) {
                ((Theseus) opponentPawn).setBeenHit(true); // Paralyze Theseus for one turn
                System.out.println("Theseus has been paralyzed!");
            }
            // Otherwise, move their pawn back by 2 positions
            else {
                int newPosition = Math.max(opponentPawn.getPosition() - 2, 0); // Avoid negative positions
                opponentPawn.moveBy(newPosition);
                System.out.println("Opponent's pawn moved back by 2 steps!");
            }
        } else {
            System.out.println("No opponent's pawn found on this path.");
        }
    }


    /**
     * Ends the round and determines whether to proceed or end the game.
     */
    private void endRound() {
        if (isGameOver()) {
            System.out.println("Game Over!");
            return; // Exit without starting a new round
        }
       changeTurn();
    }

    /**
     * fill the hands of the players with cards
     * @param deck the deck
     * @param p1 player1
     * @param p2 player2
     */
    private void fillHands(Deck deck, Player p1, Player p2){
        for(int i = 0; i < 8; i++){
            p1.addCardToHand(deck.popDeck()); // if hand gets full ,and we try to add a card, a debug print will appear in the console
            p2.addCardToHand(deck.popDeck());
        }
    }

    /**
     * Completes the player's hand by ensuring it has 8 cards.
     * Cards are drawn from the deck if there are any empty slots in the player's hand.
     * <p> Preconditions: The player is and the deck are initialized </p>
     * <p> Post conditions: The empty spaces in the hand of the Player are filled with new cards. </p>
     * @param deck the deck from which cards are drawn
     * @param P the player whose hand is being completed
     */
    public void completeHand(Deck deck, Player P){
        for(int i = 0; i < 8; i++){
            if(P.chooseCard(i) == null) {
                P.addCardToHand(deck.popDeck());
            }
        }
    }

    /**
     * Checks whether the round is over (both players have played).
     *
     * @return true if the round is over, false otherwise
     */
    private boolean isRoundOver() {
        return p1Played && p2Played;  // Both players have played
    }

    /**
     * Determines whether the game is over based on conditions (e.g., deck empty).
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return board.deck.isDeckEmpty();
    }
}
