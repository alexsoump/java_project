package src.model.Board;

import src.model.Card.Ariadne;
import src.model.Card.Card;
import src.model.Card.Minotaur;
import src.model.Card.NumberCard;
import java.util.Random;
import static src.model.Card.Palaces.*;


public class Deck {
    /**
     * Array of (future) size 100.
     * -Acts as the cards deck.
     */
    private Card[] cards;
    /**
     * array will be used as a stack
     */
    private int top = 0;
    /**
     * Constructor for class Deck.
     * -Initializes the array of size 100 with objects of type card.
     * -Each type of card should be included a certain number of times.
     * For more see game rules.
     */
        public Deck(){
            initDeck();
        }

        /** Called by constructor, initializes the cards array.
         * <b><p>
         * Post conditions: The card array is randomly filled with cards.
         * </p></b>
         */
        private void initDeck(){
            cards = new Card[100];
            int index = 0;
            //1: 20 Number Cards for every Palace
            for(int i = 0; i < 10; i++){
                cards[index++] = new NumberCard(i+1, Knossos);
                cards[index++] = new NumberCard(i+1, Knossos);
                cards[index++] = new NumberCard(i+1, Malia);
                cards[index++] = new NumberCard(i+1, Malia);
                cards[index++] = new NumberCard(i+1, Phaistos);
                cards[index++] = new NumberCard(i+1, Phaistos);
                cards[index++] = new NumberCard(i+1, Zakros);
                cards[index++] = new NumberCard(i+1, Zakros);
            }
            //2: 3 Ariadne Cards for every Palace
            for(int i = 0; i < 3; i++){
                cards[index++] = new Ariadne(Knossos);
                cards[index++] = new Ariadne(Malia);
                cards[index++] = new Ariadne(Phaistos);
                cards[index++] = new Ariadne(Zakros);
            }
            //3: 2 Minotaur Cards for every Palace
            cards[index++] = new Minotaur(Knossos);
            cards[index++] = new Minotaur(Knossos);
            cards[index++] = new Minotaur(Malia);
            cards[index++] = new Minotaur(Malia);
            cards[index++] = new Minotaur(Phaistos);
            cards[index++] = new Minotaur(Phaistos);
            cards[index++] = new Minotaur(Zakros);
            cards[index++] = new Minotaur(Zakros);

            Random rand = new Random();
            for (int i = cards.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1); // Random index between 0 and i
                // Swap cards[i] and cards[j]
                Card temp = cards[i];
                cards[i] = cards[j];
                cards[j] = temp;
            }
            top = cards.length - 1;
            // deck stack now full
        }

    /** Accessor: checks if the deck stack is empty
     *
     * @return true is the deck is empty, false otherwise
     */
    public boolean isDeckEmpty(){
        return top < 0;
    }

    /**
     * Removes and returns the top card of the deck.
     * Throws an exception if the deck is empty.
     *
     * @return the top card of the deck
     * @throws IllegalStateException if the deck is empty
     */
    public Card popDeck(){
        if (isDeckEmpty()) {
            return null;
        }
        return cards[top--];
    }

    /**
     * Getter to see how many cards are in the deck.
     * @return the top of the deck stack
     */
    public int getCardNumber(){
        return top;
    }




}
