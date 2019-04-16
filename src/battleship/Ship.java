package battleship;

/**
 * Represents a Ship in the game.
 */
public abstract class Ship {
	/**
	 * The row that contains the bow (front part of the ship).
	 */
	private int bowRow;
	
	/**
	 * The column that contains the bow (front part of the ship).
	 */
	private int bowColumn;
	
	/**
	 * The length of the ship.
	 */
	private int length;
	
	/**
	 * A boolean that represents whether the ship is going to be placed horizontally or vertically.
	 */
	private boolean horizontal;
	
	/**
	 * An array of 4 booleans that indicate whether that part of the ship has been hit or not.
	 */
	private boolean[] hit;
	
	/**
	 * Default constructor that sets the length property of the particular ship and initializes the hit array.
	 * Index 0 refers to the bow.
	 * @param length The length of the ship.
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[4];
	}
	
	/**
	 * Getter function.
	 * @return The integer length of this ship.
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Getter function.
	 * @return The integer row that contains the bow.
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	
	/**
	 * Getter function.
	 * @return The integer column that contains the bow. 
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	/**
	 * Getter function.
	 * @return The array of 4 booleans that indicates whether the ship has been hit or not.
	 */
	public boolean[] getHit() {
		return this.hit;
	}
	
	/**
	 * Getter function.
	 * @return A boolean representing whether the ship is horizontal or vertical.
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	/**
	 * Setter function.
	 * @param row The row that contains the bow.
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Setter function.
	 * @param column The column that contains the bow.
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Setter function.
	 * @param horizontal Whether the ship is placed horizontally or vertically.
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Abstract getter function implemented in subclasses.
	 * @return The type of the current ship (e.g. battleship, cruiser, etc.) as a string.
	 */
	public abstract String getShipType();
	
	/**
	 * Determines whether a ship is adjacent to the given tile given by the row/column.
	 * @param row The row that contains the bow.
	 * @param column The column that contains the bow.
	 * @param ocean The 10x10 array representing an "ocean".
	 * @return boolean indicating whether the tile has a ship adjacent to it.
	 */
	boolean isShipAdjacent(int row, int column, Ship[][] ships) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				try { // try clause used so that if accessing out of bounds array element, does not stop code (if accessing out of ocean boundaries)
					if (ships[row + i][column + j].getShipType() != "empty") {
						return true;
					}
				}
				catch (Exception e) {} // do nothing if exception caught
			}
		}
		return false;
	}
	
	/**
	 * Determines whether it is okay to put a ship of this length with its bow in this location.
	 * The ship must not overlap or touch another ship and it must not ”stick out” beyond the Ocean array.
	 * @param row The row that contains the bow.
	 * @param column The column that contains the bow.
	 * @param horizontal Whether the ship is horizontal or vertical.
	 * @param ocean The 10x10 array representing an "ocean".
	 * @return Boolean indicating if it is okay to put a ship of this length with its bow in this location.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// If outside ocean boundaries, return false
		if (row > 9 || column > 9 || row < 0 || column < 0) {
			return false;
		}
		
		// Get ship array
		Ship[][] ships = ocean.getShipArray();
		
		// Check if ship adjacent (either next to or diagonal from) or overlapping (also checks if outside ocean boundaries)
		boolean adjacent = false;
		int currRow = row;
		int currColumn = column;
		if (horizontal) { // if ship is aligned horizontally
			for (int i = 0; i < this.length; i++) {
				currColumn -= i; // decrease column to get full length of ship
				if (currColumn < 0) { // return false if outside ocean boundaries
					return false;
				}
				adjacent = this.isShipAdjacent(currRow, currColumn, ships);
				if (adjacent) { // if any ship is adjacent, return false
					return false;
				}
			}
		}
		else { // if ship is aligned vertically
			for (int i = 0; i < this.length; i++) {
				currRow -= i; // decrease row to get full length of ship
				if (currRow < 0) { // return false if outside ocean boundaries
					return false; 
				}
				adjacent = this.isShipAdjacent(currRow, currColumn, ships);
				if (adjacent) { // if any ship is adjacent, return false
					return false;
				}
			}
		}
		return true; // if this is reached, then it is okay to place ship
	}
	
	/**
	 * "Puts" the ship in the ocean.
	 * Values are assigned to the bowRow, bowColumn, and horizontal instance variables.
	 * Puts a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
	 * Horizontal ships face East (bow at right end) and vertical ships face South (bow at bottom end).
	 * @param row The row that contains the bow.
	 * @param column The column that contains the bow.
	 * @param horizontal Whether the ship is horizontal or vertical.
	 * @param ocean The 10x10 array representing an "ocean".
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Assign values to bowRow, bowColumn, horizontal
		this.bowRow = row;
		this.bowColumn = column;
		this.horizontal = horizontal;
				
		// Put reference to ship in the ships array in the Ocean object
		if (horizontal) { // if ship is being placed horizontally
			for (int i = 0; i < this.length; i++) // used to place length number of references in the ships array
			{
				ocean.getShipArray()[bowRow][bowColumn - i] = this; // if ship is placed horizontal, bowRow stays the same and references are added using bowColumn and the length of ship
			}
		}
		else { // if ship is being placed vertically
			for (int i = 0; i < this.length; i++) // used to place length number of references in the ships array
			{
				ocean.getShipArray()[bowRow - i][bowColumn] = this; // if ship is placed vertical, bowColumn stays the same and references are added using bowRow and the length of ship
			}
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, marks that part of the ship as “hit”.
	 * @param row The row that contains the bow.
	 * @param column The column that contains the bow.
	 * @return Boolean indicating whether the ship has been hit.
	 */
	boolean shootAt(int row, int column) {
		// If ship already sunk, return false
		if (this.isSunk()) {
			return false;
		}
		
		// Check if ship occupies given row and column, if so then check if ship has been hit there, if not then mark as hit
		if (this.horizontal) { // if horizontal
			if (row == bowRow) { // if shooting at the ship's row
				for (int i = 0; i < this.length; i++) {
					if (bowColumn - i == column) { // if ship occupies column and has not been hit there, then mark as hit
						this.hit[i] = true;
						return true;
					}
				}
			}
		}
		else { // if vertical
			if (column == bowColumn) { // if shooting at the ship's column
				for (int i = 0; i < this.length; i++) {
					if (bowRow - i == row) { // if ship occupies row and has not been hit there, then mark as hit
						this.hit[i] = true;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Determines whether every part of the ship has been hit.
	 * @return Boolean indicating whether every part of the ship has been hit.
	 */
	boolean isSunk() {
		// Go through hit array, check if any element is false and return false if so
		for (int i = 0; i < this.length; i++) {
			if (this.hit[i] = false) {
				return false;
			}
		}
		
		// If no return false is reached, then hit array must be all true and so return true
		return true;
	}
	
	/** 
	 * Overrides default toString() function.
	 * Returns a single-character String to use in the Ocean's print method.
	 * @return "s" if the ship has been sunk and "x" if it has not been sunk.
	 */
	@Override
	public String toString() {
		// if sunk, return "s"
		if (this.isSunk()) {
			return "s";
		}
		else { // else, return "x"
			return "x";
		}
	}
}