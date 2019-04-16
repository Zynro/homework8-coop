package battleship;

/**
 * Represents a Destroyer in the game.
 * Inherited from Ship class.
 */
public class Destroyer extends Ship {
	
	/**
	 * Default constructor, calls the super constructor with length = 2 for Destroyer.
	 */
	public Destroyer() {
		super(2);
	}

	/**
	 * @return The type of this ship as a string ("destroyer").
	 */
	@Override
	public String getShipType() {
		return "destroyer";
	}
}
