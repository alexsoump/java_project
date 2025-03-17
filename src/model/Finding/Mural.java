package src.model.Finding;

/**
 * Represents a mural (fresco) finding in the game, which has a point value depending on its type.
 * Murals are divided into different types, and their point values depend on the specific type.
 */
public class Mural extends Finding {
    /**
     * The point value associated with the mural.
     * Murals of type 0, 1, or 2 have 20 points, and others have 15 points.
     */
    private final int points;
    /**
     * The type of the mural (fresco).
     * This indicates which specific fresco type this mural belongs to.
     */
    private Murals type;
    /**
     * Constructs a Mural with a specific type. The points are set based on the type of the mural.
     * <p>
     * Preconditions:
     * - The `type` parameter must not be null.
     * </p>
     * <p>
     * Post conditions:
     * - The mural is initialized with the specified type.
     * - The points are set to 20 if the type ordinal is less than 3, or 15 otherwise.
     * </p>
     *
     * @param type the type of the mural (fresco)
     */
    public Mural(Murals type){
        this.type = type;
        if(type.ordinal() < 3) {
            this.points = 20;
        }else{
            this.points = 15;
        }
    }

    /**
     * Retrieves the point value associated with the mural.
     * <p>
     * Preconditions:
     * - The mural must be properly initialized.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the point value of the mural (20 or 15).
     * </p>
     *
     * @return the point value of the mural
     */
    public int getPoints(){
        return this.points;
    }
    /**
     * Retrieves the type of the mural.
     * <p>
     * Preconditions:
     * - The mural must be properly initialized with a valid type.
     * </p>
     * <p>
     * Post conditions:
     * - Returns the type of the mural (Fresco).
     * </p>
     *
     * @return the type of the mural
     */
    public Murals getType(){return this.type;}

    /**
     * Returns a string representation of the mural, including its type and point value.
     * <p>
     * Preconditions:
     * - None.
     * </p>
     * <p>
     * Post conditions:
     * - Returns a string representation describing the mural's type and point value.
     * </p>
     *
     * @return a string representation of the mural
     */
    @Override
    public String toString(){
        return "Mural<" + getType() +">";
    }

}

