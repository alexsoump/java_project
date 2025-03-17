package src.model.Finding;

/**
 * Represents a special finding in the game, such as a rare artifact.
 * Special findings have a type (e.g., "DaxtylidiMinwa") and a point value that depends on the type.
 */
public class SpecialFinding extends Finding {

    /**
     * The point value associated with the special finding.
     * Different types of special findings have different point values.
     */
    private final int points;

    /**
     * The type of the special finding (e.g. "DaxtylidiMinwa").
     */
    private SpecialFindings type;

    /**
     * Constructs a SpecialFinding with a specific type.
     * The points are set based on the type of the special finding.
     * <p>
     * Preconditions:
     * - The `type` parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The special finding is initialized with the specified type.
     * - If the type is `DaxtylidiMinwa`, the points are set to 35; otherwise, the points are set to 25.
     * </p>
     *
     * @param type the type of the special finding (e.g., "DaxtylidiMinwa")
     */
    public SpecialFinding(SpecialFindings type) {
        this.type = type;
        if (type == SpecialFindings.DaxtylidiMinwa) {
            this.points = 35;
        } else {
            this.points = 25;
        }
    }

    /**
     * Retrieves the point value associated with the special finding.
     * <p>
     * Preconditions:
     * - The special finding must be properly initialized.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the point value of the special finding (either 35 or 25).
     * </p>
     *
     * @return the point value of the special finding
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * Retrieves the type of the special finding.
     * <p>
     * Preconditions:
     * - The special finding must be properly initialized with a valid type.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the type of the special finding.
     * </p>
     *
     * @return the type of the special finding (e.g., "DaxtylidiMinwa")
     */
    public SpecialFindings getType() {
        return this.type;
    }

    /**
     * Returns a string representation of the special finding, including its type and point value.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns a string describing the special finding's type and point value.
     * </p>
     *
     * @return a string representation of the special finding
     */
    @Override
    public String toString() {
        return "SpecialFinding<" + getType() + ">" + " Points<" + getPoints() + ">";
    }
}
