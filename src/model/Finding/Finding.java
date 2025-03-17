package src.model.Finding;
/**
 * Represents an abstract finding in the game.
 * Each finding has a specific point value associated with it.
 */
public abstract class Finding {
    /**
     * The point value associated with the finding.
     * Subclasses should initialize this field with a specific point value.
     */
    int points;
    /**
     * Retrieves the point value of the finding.
     * <p>
     * Preconditions:
     * - The finding must be properly initialized with a valid point value.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the point value associated with the finding.
     * </p>
     *
     * @return the point value of the finding
     */
    public int getPoints() {
        return points;
    }
}


