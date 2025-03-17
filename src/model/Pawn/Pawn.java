package src.model.Pawn;

import src.model.Card.Palaces;

/**
 * Represents a pawn in the game.
 * A pawn belongs to a specific palace, has a position on the path, and can be hidden or revealed.
 */
public class Pawn {

    /**
     * The palace to which this pawn belongs.
     */
    private Palaces palace;

    /**
     * The current position of the pawn on the path.
     * - A value of -1 indicates the pawn is not yet on the path.
     * - A value of 0 or greater indicates the position on the path.
     */
    private int position;

    /**
     * Indicates whether the pawn is currently hidden.
     */
    private boolean isHidden;

    private boolean isPlaced;

    /**
     * Constructs a pawn with its initial state.
     * The pawn starts with a position of -1 (not on the path) and is hidden.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The pawn is initialized with a position of -1.
     * - The pawn is hidden by default.
     * - The palace is not set initially.
     * </p>
     */
    public Pawn() {
        this.position = -1;
        isPlaced = false;
        isHidden = true;
    }

    /**
     * Reveals the pawn, making it visible to other players.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - The `isHidden` field is set to false.
     * </p>
     */
    public void reveal() {
        isHidden = false;
    }

    /**
     * Checks whether the pawn is hidden.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns true if the pawn is hidden; false otherwise.
     * </p>
     *
     * @return true if the pawn is hidden, false otherwise
     */
    public boolean isHidden() {
        return isHidden;
    }

    public boolean isPlaced(){
        return isPlaced;
    }
    public void setPlaced(boolean b){
        isPlaced = b;
        position = 0;
    }

    /**
     * Retrieves the current position of the pawn on the path.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the position of the pawn.
     * - The position is -1 if the pawn is not on the path.
     * </p>
     *
     * @return the position of the pawn
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * sets a new position for the pawn
     * <p>
     *     Precondition: Move is valid
     * </p>
     * <p>
     *     Post condition: position is updated
     * </p>
     * @param spaces the number of spaces it moves
     */
    public void moveBy(int spaces){
        if(spaces < 0) throw new IllegalArgumentException("Spaces must be positive");

        if(this.position + spaces > 8){
            this.position = 8;
            return;
        }else if(this.position == -1){ // if pawn is not on a path
            this.position = 0;
        }
        this.position += spaces;
    }

    /**
     * Retrieves the palace to which this pawn belongs.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the palace associated with the pawn.
     * - Returns null if the palace has not been set.
     * </p>
     *
     * @return the palace associated with this pawn
     */
    public Palaces getPalace() {
        return this.palace;
    }

    /**
     * Sets the palace to which this pawn belongs.
     * <p>
     * Preconditions:
     * - The `pal` parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The `palace` field is updated to the specified value.
     * </p>
     *
     * @param pal the palace to associate with this pawn
     */
    public void setPalace(Palaces pal) {
        this.palace = pal;
    }


    /**
     * Gets the points a pawn has according to it's position on the path.
     * <p>
     *     Precondition: Pawn exists
     * </p>
     * <p>
     *     Post conditions: The points of its position are returned.
     * </p>
     * @return the points of the pawn which contribute to the player's total points
     */
    public int getScore(){
        int[] positionPts = {-20, -15, -10, 5, 10, 15, 30, 35, 50};
        if(position < 0 || position > 8) return 0; // invalid pawn position
        return positionPts[position];
    }
}
