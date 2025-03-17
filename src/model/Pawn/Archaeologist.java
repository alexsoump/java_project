package src.model.Pawn;

/**
 * Represents an archaeologist pawn in the game.
 * The archaeologist inherits all functionality from the generic pawn but can also
 * interact with findings during gameplay.
 */
public class Archaeologist extends Pawn {

    /**
     * Constructs an Archaeologist pawn with its initial state.
     * The pawn starts with a position of -1 (not on the path) and is hidden by default.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The archaeologist is initialized with the default pawn state.
     * - The position is set to -1.
     * - The pawn is hidden.
     * </p>
     */
    public Archaeologist() {
        super();
    }
}
