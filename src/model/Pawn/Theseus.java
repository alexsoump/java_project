package src.model.Pawn;

import src.model.Finding.Finding;
import src.model.Finding.Mural;

/**
 * Represents the Theseus pawn in the game.
 * Theseus has the ability to destroy findings, except for murals, and can be affected by Minotaur cards.
 * If Theseus is hit by a Minotaur card, he is out for the next round.
 */
public class Theseus extends Pawn {

    /**
     * Indicates whether Theseus has been hit by a Minotaur card.
     * If true, Theseus cannot act in the current round.
     */
    private boolean beenHit = false;

    /**
     * Constructs a Theseus pawn with its initial state.
     * The pawn starts with a position of -1 (not on the path), is hidden by default,
     * and is not affected by a Minotaur card.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Theseus is initialized with default pawn properties.
     * - The position is set to -1.
     * - The pawn is hidden.
     * - `beenHit` is set to false.
     * </p>
     */
    public Theseus() {
        super();
    }

    /**
     * Destroys the specified finding, except for murals.
     * If the finding is a mural, it cannot be destroyed.
     * <p>
     * Preconditions:
     * - The `finding` parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - If the finding is not a mural, it is destroyed (removed from its position).
     * - If the finding is a mural, no action is performed.
     * </p>
     *
     * @param finding the finding to destroy
     */
    void destroyFinding(Finding finding) {
        finding = null; // Simulate destroying the finding

    }

    /**
     * Checks whether Theseus has been hit by a Minotaur card.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns true if Theseus has been hit and is unavailable for the current round.
     * - Returns false otherwise.
     * </p>
     *
     * @return true if Theseus has been hit by a Minotaur card, false otherwise
     */
    public boolean isBeenHit() {
        return beenHit;
    }

    /**
     * Sets the state of Theseus being hit by a Minotaur card.
     * If true, Theseus is unavailable for the current round.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The `beenHit` field is updated to the specified value.
     * </p>
     *
     * @param beenHit true if Theseus has been hit by a Minotaur card, false otherwise
     */
    public void setBeenHit(boolean beenHit) {
        this.beenHit = beenHit;
    }
}
