package src.model.Position;

/**
 * Represents a position on the game board.
 * Each position has a score, which can be positive or negative, affecting the player's progress.
 */
public class Position {

    /**
     * The score associated with this position.
     * The score determines the value of the position (can be positive or negative).
     */
    private int score;

    /**
     * Constructs a Position with a default score of 0.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The position is initialized with a score of 0.
     * </p>
     */
    public Position() {
        this.score = 0;
    }

    /**
     * Retrieves the score associated with this position.
     * <p>
     * Preconditions:
     * - The position must be properly initialized.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the score of this position.
     * </p>
     *
     * @return the score of the position
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score for this position.
     * <p>
     * Preconditions:
     * - The score parameter must be a valid integer.
     * </p>
     * <p>
     * Post conditions:
     * - The position's score is updated to the specified value.
     * </p>
     *
     * @param score the new score to assign to the position
     */
    public void setScore(int score) {
        this.score = score;
    }
}
