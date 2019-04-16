package battleship;

/**
 * Represents an Empty Sea tile in the game.
 * Inherited from Ship class.
 */
public class EmptySea extends Ship {
	
	/**
	 * Default constructor, calls the super constructor with length = 1.
	 */
	public EmptySea() {
		super(1);
	}
	
	/** 
	 * Overrides default Ship function shootAt(row, column).
	 * @return false to indicate that nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * Overrides default Ship function isSunk().
	 * @return false to indicate that nothing was sunk.
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 * Overrides default Ship function toString().
	 * @return single character "-" String to use in the Ocean's print method.
	 */
	@Override
	public String toString() {
		return "-";
	}

	/**
	 * @return The type of this "ship" as a string ("empty").
	 */
	@Override
	public String getShipType() {
		return "empty";
	}

}
