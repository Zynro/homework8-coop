package battleship;

import java.util.Random;

/**
 * Represents the Ocean in the game.
 */
public class Ocean {
	
	/**
	 * The 2d array of the ocean, containing Ship objects.
	 */
	private Ship[][] ships = new Ship[10][10];
	/*
	 * 2d Hit array for the ocean
	 */
	private boolean[][] hitArray = new boolean[10][10];
	/**
	 * Total shots fired
	 */
	private int shotsFired;
	/*
	 * Total shots fired that hit a ship
	 */
	private int hitCount;
	/**
	 * Number of ships sunk
	 */
	private int shipsSunk;
	
	/**
	 * Constructor for ocean. Places EmptySea Ship objects into every slot. 
	 * Instantiates beginning variables for the game.
	 */
	public Ocean() {
		for(int i = 0; i<10;i++) {
			for(int b = 0; b<10;b++) {
				ships[i][b] = new EmptySea();
				hitArray[i][b] = false;
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	/**
	 * Places all ships randomly onto the board.
	 */
	void placeAllShipsRandomly() {
		Random random = new Random();
		boolean placed = false;
		boolean horizontal;
		
		// Instantiates all the ship classes
		Ship battleship = new Battleship();
		// If more than one, puts them into a ship array to call them with Ship[x]
		Ship[] cruisers = new Cruiser[2]; 
		Ship[] destroyers = new Destroyer[3];
		Ship[] submarines = new Submarine[4];
		
		// While the ship isn't placed
		while(placed == false) {
			//Generates random values to check if a ship can be placed, starting with biggest ships.
			int column = random.nextInt(10);
			int row = random.nextInt(10);
			// Randomizes direction
			horizontal = random.nextBoolean();
			// If it's okay to place the ship there, places it and sets placed to true ending the loop
			if (battleship.okToPlaceShipAt(row, column, horizontal, this) == true) {
				battleship.placeShipAt(row, column, horizontal, this);
				placed = true;
			}else {
				continue;
			}
		}	
		
		// For every ship in each other ship array, repeats the above process.
		for(int i = 0; i<cruisers.length; i++) {
			placed = false;
			cruisers[i] = new Cruiser();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (cruisers[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					cruisers[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		for(int i = 0; i<destroyers.length; i++) {
			placed = false;
			destroyers[i] = new Destroyer();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (destroyers[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					destroyers[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		for(int i = 0; i<submarines.length; i++) {
			placed = false;
			submarines[i] = new Submarine();
			while(placed == false) {
				int column = random.nextInt(10);
				int row = random.nextInt(10);
				horizontal = random.nextBoolean();
				if (submarines[i].okToPlaceShipAt(row, column, horizontal, this) == true) {
					submarines[i].placeShipAt(row, column, horizontal, this);
					placed = true;
				}else {
					continue;
				}
			}
		}
		
	}
	/**
	 * Checks to see if a coordinate point in the ocean is occupied by a ship
	 * @param row The row coordinate for the check
	 * @param column The row coordinate for the check
	 * @return boolean Occupation of coordinate
	 */
	boolean isOccupied(int row, int column) {
		// If the type of ship in that coordinate is empty, return true
		if(ships[row][column].getShipType() != "empty") {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Shoots at that specific coordinate, increasing shotsfired, and setting that coordinate in the hitarray to
	 * true for shot at.
	 * @param row The row coordinate for the shot
	 * @param column The row coordinate for the shot
	 * @return boolean Status of shot if it hit a ship or emptysea
	 */
	boolean shootAt(int row, int column) {
		shotsFired++;
		hitArray[row][column] = true;
		if((ships[row][column].getShipType() != "empty") && (ships[column][row].isSunk() == false)) {
			hitCount++;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Gets the amount of shots fired
	 * @return Fired shots.
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	/**
	 * Gets the amount of hits on ships
	 * @return The amount of hits on ships
	 */
	int getHitCount() {
		return this.hitCount;
	}
	/**
	 * Gets the amount of ships sunk
	 * @return The amount of ships sunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * Increases the ships sunk count by 1
	 */
	public void addShipSunk() {
		this.shipsSunk++;
	}
	
	/**
	 * Checks if the game is over or not based on total number of sunk ships
	 * @return boolean Status of the game
	 */
	boolean isGameOver() {
		if (this.shipsSunk == 10){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Gets the ship array
	 * @return Returns the ship array
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	/**
	 * Prints the current state of the ocean, with proper marks for each type of status
	 * Period (.) means not shot at
	 * Dash (-) means missed shot
	 * an X (x) means shot hit a ship
	 * an S (s) means a sunk ship
	 */
	void print() {
		//Generates output string
		String output = "";
		//Generates output array, each slot is a different line on the ocena board.
		String[] printArray = new String[11]; // Length 11 to account for top row of 0-9
		for(int i=0;i<printArray.length;i++) { // For the entire length of the array
			if(i==0) { // If its the first slot, set the string to the numbers 0-9
				printArray[i] = "  0 1 2 3 4 5 6 7 8 9";
				continue;
			}else {
				try { // otherwise, ignoring all errors...
					printArray[i] = Integer.toString(i-1); // Set the slot to the string version of the slot minus 1
					for (int column=0;column<ships[i-1].length;column++) { // And for every column
						if (hitArray[i-1][column] == false){ // If the hitarray has not been shot at
							printArray[i] = printArray[i] + " " + "."; // Ad a space and a period to represent that
						}else { // Otherwise
							// Print the representation of the ship, either hit or sunk, after a space.
							printArray[i] = printArray[i] + " " + ships[i-1][column].toString();
						}
					}
				}
				catch (Exception e) {} // Catch and ignore all exceptions
			}
		}
		output = String.join("\n", printArray); // Finally, join all the "rows" of the print array into a string
		System.out.println(output); // Print that string
	}
	
	
}