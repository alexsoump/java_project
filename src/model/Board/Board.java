package src.model.Board;

import src.model.Card.*;
import src.model.Finding.*;
import src.model.Position.*;
import java.util.Random;

/**
 * Represents the game board, including paths to the four palaces, the deck of cards,
 * and tracking of the last cards played by each player.
 */
public class Board {

    /**
     * The paths leading to the four palaces.
     * Each path is represented as a 2D array of positions (4 palaces x 9 positions per path).
     */
    public Position[][] Paths;

    /**
     * The deck containing all cards (Number cards, Ariadne cards, and Minotaur cards).
     * Total size is 100 cards.
     */
    public Deck deck = new Deck();

    /**
     * Tracks the last played card for each player and palace.
     * Used to determine whether a player can play a specific number card next round.
     * Indexed as [player][palace].
     */
    NumberCard[][] lastPlayed = new NumberCard[4][2];

    /**
     * Findings are stored and the shuffled to be added to the paths.
     */
    private Finding[] findingsArray;

    /**
     * Constructs the Board object.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The paths are created but not populated with findings.
     * </p>
     */
    public Board() {
        shuffleFindings();
        fillPaths();
    }

    /**
     * Fills the findings array with findings and shuffles it
     * <p>
     *     Preconditions: the findingsArray is declared
     * </p>
     * <p>
     *     Post conditions: the array is now full of findings
     * </p>
     * */
    private void shuffleFindings(){
        findingsArray = new Finding[20]; // 4 special, 6 frescoes, 10 snake goddess
        int index = 0;
        // add the specials
        findingsArray[index++] = new SpecialFinding(SpecialFindings.DiskosFaistou);
        findingsArray[index++] = new SpecialFinding(SpecialFindings.RytoZakrou);
        findingsArray[index++] = new SpecialFinding(SpecialFindings.KosmhmaMaliwn);
        findingsArray[index++] = new SpecialFinding(SpecialFindings.DaxtylidiMinwa);
        // add the frescoes/murals
        findingsArray[index++] = new Mural(Murals.FourDolphins);
        findingsArray[index++] = new Mural(Murals.HappyMan);
        findingsArray[index++] = new Mural(Murals.OnTheBull);
        findingsArray[index++] = new Mural(Murals.TwoGuys);
        findingsArray[index++] = new Mural(Murals.ThreeWomen);
        findingsArray[index++] = new Mural(Murals.WomanPortrait);
        // add the snake goddesses
        for(int i = 0; i < 10; i++){
            findingsArray[index++] = new SnakeGoddess();
        }

        /*  shuffle:  */
        Random random = new Random();
        for (int i = findingsArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // random index from 0 to i
            Finding temp = findingsArray[i];   // swap
            findingsArray[i] = findingsArray[j];
            findingsArray[j] = temp;
        }

    }


    /**
     * Populates the paths with findings and other positions.
     * <p>
     * Preconditions:
     * - The Paths array must be initialized and empty.
     * - Findings and simple positions must be available for placement.
     * </p>
     * <p>
     * Post conditions:
     * - All paths are filled with findings at the appropriate positions.
     * - Remaining positions are filled with simple positions.
     * </p>
     */
    private void fillPaths() {
        Paths = new Position[4][9];
        // Fill paths with default positions
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                if (Paths[i][j] == null) { // if uninitialized
                    if(j == 1 || j == 3 || j == 5 || j == 7 || j == 8) {
                        Paths[i][j] = new FindingPosition(findingsArray[index++]); // add a finding from the array
                    }else{
                        Paths[i][j] = new SimplePosition(); // default position
                    }
                }
            }
        }

        // Add points to positions
        int pts = -20;
        for (int i = 0; i < 9; i++) {
            // Avoid NullPointerException by checking for null values
            if (Paths[0][i] != null) Paths[0][i].setScore(pts);
            if (Paths[1][i] != null) Paths[1][i].setScore(pts);
            if (Paths[2][i] != null) Paths[2][i].setScore(pts);
            if (Paths[3][i] != null) Paths[3][i].setScore(pts);

            pts += 5;
            if (i == 2) pts = 5;
            else if (i == 5) pts = 30;
            else if (i == 7) pts = 50;
        }
    }


}
