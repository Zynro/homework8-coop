package battleship;

/**
 * Represents a Submarine in the game.
 * Inherited from Ship class.
 */
public class Submarine extends Ship {
	
	/**
	 * Default constructor, calls the super constructor with length = 1 for Submarine.
	 */
	public Submarine() {
		super(1);
	}

	/**
	 * @return The type of this ship as a string ("submarine").
	 */
	@Override
	public String getShipType() {
		return "submarine";
	}
	@Override
	public String toString() {
		return "S";
	}
}
