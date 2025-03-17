package src.model.Finding;

/**
 * Represents a Snake Goddess finding in the game.
 * The Snake Goddess is a special type of finding that has its own unique properties.
 */
public class SnakeGoddess extends Finding {

    /**
     Constructor
     */
    public SnakeGoddess(){
        super();
    }
    /**
     * Returns a string representation of the Snake Goddess finding.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns a string indicating this is a Snake Goddess finding.
     * </p>
     *
     * @return a string representation of the Snake Goddess finding
     */
    @Override
    public String toString() {
        return "Finding<SnakeGoddess>";
    }
}
