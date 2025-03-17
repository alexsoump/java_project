package src.model.Player;

import src.model.Board.Board;
import src.model.Board.Deck;
import src.model.Card.*;
import src.model.Finding.*;
import src.model.Pawn.Archaeologist;
import src.model.Pawn.Pawn;
import src.model.Pawn.Theseus;
import src.model.Position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game, who has a score, a hand of cards,
 * and a collection of findings.
 * <p>
 * A player interacts with the game by playing cards, moving pawns, and
 * collecting findings during the game rounds.
 * </p>
 */
public class Player {
    private final String name;
    public int playerIndex;
    private int score; // The player's current score
    private Pawn[] pawns;
    private ArrayList<Finding> findings; // The findings collected by the player
    private Card[] hand; // The cards currently in the player's hand
    private NumberCard[] lastCardPlayed; // Tracks the last card played by the player for each path

    /**
     * Constructs a Player with an initial score of 0, an empty hand,
     * and an uninitialized set of last played cards for each path.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        pawns = new Pawn[4];
        pawns[0] = new Archaeologist();
        pawns[1] = new Archaeologist();
        pawns[2] = new Archaeologist();
        pawns[3] = new Theseus();
        this.score = 0;
        this.findings = new ArrayList<>();
        this.hand = new Card[8];
        this.lastCardPlayed = new NumberCard[4];
        initLastCardPlayed();
    }

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds the specified score to the player's total score.
     *
     * @param score the score to be added to the player's total
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Retrieves the current score of the player.
     *
     * @return the player's current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to add to the player's hand
     * @throws IllegalStateException if the player's hand is full
     */
    public void addCardToHand(Card card) {
        for (int i = 0; i < 8; i++) {  // Loop through hand slots
            if (hand[i] == null) {    // Find the first empty slot
                hand[i] = card;       // Add the card
                System.out.println(this.name + " :card added to hand.");
                return;               // Exit the method immediately
            }
        }
        // If no empty slot is found
        System.out.println("Hand is full! Cannot add card.");
    }

    /**
     * Chooses a card from the player's hand at the specified index.
     *
     * @param index the index of the card to choose
     * @return the card chosen from the player's hand
     * @throws IndexOutOfBoundsException if the index is outside the valid range
     */
    public Card chooseCard(int index) {
        if(index > 7 || index < 0){
            throw new IndexOutOfBoundsException("Index should be within [0,8].");
        }
        return hand[index];
    }

    /**
     * once a number card is played it has to be saved, for the next card on the same palace must be of larger value
     * @param palace card palace
     * @param card the card itself, perhaps useless
     */
    public void setLastCardPlayed(Palaces palace, NumberCard card){
        lastCardPlayed[palace.ordinal()] = card;
    }

    /**
     * Initialize the array to save the last number card played for each palace.
     * Preconditions: none
     * Post conditions: array is initialized
     */
    private void initLastCardPlayed(){
        for(int i = 0; i < lastCardPlayed.length; i++){
            lastCardPlayed[i] = new NumberCard(0, Palaces.values()[i]);
        }
    }

    /**
     * Retrieves the last card played by the player for the specified palace.
     *
     * @param palace the palace associated with the card
     * @return the last card played for the specified palace, or null if no card has been played
     */
    public NumberCard getLastCard(Palaces palace) {
        return lastCardPlayed[palace.ordinal()];
    }

    /**
     * Discards a card from the player's hand and draws a new card from the deck.
     *
     * @param index the index of the card in the hand
     */
    public void rejectCard(int index) {
        if(index < 0 || index > 8) throw new IndexOutOfBoundsException();
        hand[index] = null;
    }

    /**
     * Moves the player's pawn based on the card played.
     *
     * @param card the card used to move the pawn
     */
    public void movePawn(Card card) {
        int pathIndex = card.getPalace().ordinal(); // Get the path index based on palace

        // 1. Check if any pawn is already on the path
        if(getPawnForPath(pathIndex) != null) {
            Pawn pawn = getPawnForPath(pathIndex);
            if(card instanceof Ariadne) pawn.moveBy(2); // probably checked already
            else if(pawn.getPosition() + ((NumberCard) card).getValue() > 8){
                pawn.moveBy(8 - pawn.getPosition()); // get the pawn to the end
            }else{
                pawn.moveBy(((NumberCard) card).getValue()); // move the pawn on the path
            }
            return; // exit the function
        }else {
            // 2. If no pawn is on the path, move the next available pawn
            for (Pawn pawn : pawns) {
                if (!pawn.isPlaced()) { // Find the first unplaced pawn
                    pawn.setPlaced(true); // Mark as placed
                    pawn.setPalace(card.getPalace()); // Assign palace to this pawn
                    pawn.moveBy(((NumberCard) card).getValue()); // Move the pawn
                    return;
                }
            }
        }

        // 3. If all pawns are placed and no match, print error
        System.out.println("No available pawns to move!");
    }

    /**
     * Add finding to the players pocket.
     * @param finding the finding to be added
     */
    public void addFinding(Finding finding){
        findings.add(finding);
    }

    /**
     * get the pawn for a path
     * @param i the path[0,4]
     * @return the pawn the player has, null if there is no pawn
     */
    public Pawn getPawnForPath(int i){
        if(i < 0 || i > 4) throw new IllegalArgumentException("Index out of bounds!");
        if(pawns[0].getPalace()!= null && pawns[0].getPalace().ordinal() == i) return pawns[0];
        if(pawns[1].getPalace()!= null && pawns[1].getPalace().ordinal() == i) return pawns[1];
        if(pawns[2].getPalace()!= null && pawns[2].getPalace().ordinal() == i) return pawns[2];
        if(pawns[3].getPalace()!= null && pawns[3].getPalace().ordinal() == i) return pawns[3];
        System.out.println("No Pawns are in this path!"); // TODO: this line gets printed too many times
        return null;
    }

    /**
     * Searches in the cards of the player and returns whether they have the card
     * @param card the card
     * @return -1 if they don't have the card, the card index otherwise
     */
    public int hasCard(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != null && hand[i].equals(card)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * finds the frescoes of the player and returns them
     * @return an arraylist of the frescoes
     */
    public ArrayList<Mural> getFrescoes() {
        ArrayList<Mural> frescoesList = new ArrayList<>();
        for (Finding finding : findings) {
            if (finding instanceof Mural) {
                frescoesList.add((Mural) finding);
            }
        }
        return frescoesList;
    }

    /**
     * finds the special findings of the player and returns them
     * @return an arraylist of the findings
     */
    public ArrayList<SpecialFinding> getSpecialFindings(){
        ArrayList<SpecialFinding> specialFindingsList = new ArrayList<>();
        for (Finding finding : findings) {
            if (finding instanceof SpecialFinding) {
                specialFindingsList.add((SpecialFinding) finding);
            }
        }
        return specialFindingsList;

    }

    /**
     * finds the snakeGoddesses of the player and returns them
     * @return an arraylist of the snakeGoddesses
     */
    public ArrayList<SnakeGoddess> getSnakeGoddesses(){
        ArrayList<SnakeGoddess> snakeGoddessesList = new ArrayList<>();
        for (Finding finding : findings) {
            if (finding instanceof SnakeGoddess) {
                snakeGoddessesList.add((SnakeGoddess) finding);
            }
        }
        return snakeGoddessesList;

    }

    /**
     * calculates the score of the players
     */
    public void calculateScore(){
        int findingsScore = 0;
        int pawnsScore = 0;
        for(Finding finding : findings){
            findingsScore+= finding.getPoints();
        }
        pawnsScore+= pawns[0].getScore();
        pawnsScore+= pawns[1].getScore();
        pawnsScore+= pawns[2].getScore();
        pawnsScore+= pawns[3].getScore();

        this.score = findingsScore + pawnsScore;
    }

    /**
     * looks up the finding and returns true if it finds it
     * @param finding the finding to be found
     * @return true if the player has it, false otherwise
     */
    public boolean hasSpecial(SpecialFinding finding) {
        for (Finding findingCount : findings) {
            if (findingCount instanceof SpecialFinding) {
                if (findingCount.equals(finding)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Getter: gets the number of snake goddesses a player has
     * @return the number of snake goddesses
     */
    public int getSnakeGoddessCount(){
        int count = 0;
        for (Finding finding : findings) {
            if (finding instanceof SnakeGoddess) {
                count++;
            }
        }
        return count;
    }

}
