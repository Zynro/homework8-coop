package battleship;

/**
 * Represents a Cruiser in the game.
 * Inherited from Ship class.
 */
public class Cruiser extends Ship {
	
	/**
	 * Default constructor, calls the super constructor with length = 3 for Cruiser.
	 */
	public Cruiser() {
		super(3);
	}

	/**
	 * @return The type of this ship as a string ("cruiser").
	 */
	@Override
	public String getShipType() {
		return "cruiser";
	}
	@Override
	public String toString() {
		return "C";
	}
}
