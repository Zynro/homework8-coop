package battleship;

/**
 * Represents a Battleship in the game.
 * Inherited from Ship class.
 */
public class Battleship extends Ship {
	
	/**
	 * Default constructor, calls the super constructor with length = 4 for Battleship.
	 */
	public Battleship() {
		super(4);
	}
	
	/**
	 * @return The type of this ship as a string ("battleship").
	 */
	@Override
	public String getShipType() {
		return "battleship";
	}
	@Override
	public String toString() {
		return "B";
	}
}
