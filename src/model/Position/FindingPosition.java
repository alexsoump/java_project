package src.model.Position;

import src.model.Finding.Finding;
import src.model.Finding.Mural;

/**
 * Represents a position on the game board that contains a finding.
 * This class extends `Position` and allows the manipulation of a finding at that position.
 */
public class FindingPosition extends Position {

    /**
     * Indicates whether the finding at this position is available to be taken or destroyed.
     */
    private boolean isFindingAvailable = true;

    /**
     * The finding stored at this position, which can be accessed or destroyed.
     */
    private Finding findingStored = null;

    /**
     * Constructs a FindingPosition with a specific finding.
     * The finding is stored at this position, and the position is initialized with a score of 0.
     * <p>
     * Preconditions:
     * - The `finding` parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The position is initialized with the specified finding.
     * </p>
     *
     * @param finding the finding to store at this position
     */
    public FindingPosition(Finding finding) {
        super();
        this.findingStored = finding;
    }

    /**
     * Retrieves the finding stored at this position.
     * <p>
     * Preconditions:
     * - The position must be properly initialized.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the finding stored at this position, or null if there is no finding.
     * </p>
     *
     * @return the finding stored at this position
     */
    public Finding getFinding() {
        return this.findingStored;
    }

    /**
     * Destroys the finding at this position.
     * If the finding is a `Mural`, it cannot be destroyed.
     * Otherwise, the finding is removed, and its availability is marked as false.
     * <p>
     * Preconditions:
     * - The position must contain a finding.
     * </p>
     * <p>
     * Post conditions:
     * - If the finding is not a Mural, it is destroyed, and its availability is set to false.
     * - If the finding is a Mural, no changes are made, and the finding remains.
     * </p>
     */
    public void destroyFinding(){
        if(findingStored instanceof Mural) return; // cannot destroy murals
        isFindingAvailable = false; // destroy
    }
}
